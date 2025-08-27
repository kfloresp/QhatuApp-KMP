package com.rgk.qhatu.feature.product.data.repository

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.feature.product.data.database.dao.ProductDao
import com.rgk.qhatu.feature.product.data.remote.ProductRemoteDataSource
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.common.util.getCurrentTimestamp
import com.rgk.qhatu.feature.product.data.database.dao.ImageProductDao
import com.rgk.qhatu.feature.product.data.database.entity.ImageProductEntity
import com.rgk.qhatu.feature.product.domain.mapper.toDomain
import com.rgk.qhatu.feature.product.domain.mapper.toEntity
import com.rgk.qhatu.feature.product.domain.model.ImageProduct
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.domain.repository.ProductRepository
import com.rgk.qhatu.shared.SharedImage
import com.rgk.qhatu.shared.SharedImageStorage

class ProductRepositoryImpl(
    private val sourceRemote: ProductRemoteDataSource,
    private val sourceLocal: ProductDao,
    private val imageSourceLocal: ImageProductDao,
) : ProductRepository {

    override suspend fun fetchAllProducts(): SyncResult<List<Product>> = safeCall {
        sourceLocal.getProductsWithDetails()?.map { source ->
            val productImageList: List<ImageProduct> =
                imageSourceLocal.getImagesForProduct(source.id).map {
                    it.toDomain()
                }
            source.toDomain()
                .copy(imageProduct = productImageList)
        } ?: emptyList()
    }

    override suspend fun fetchProductById(productId: String): SyncResult<Product?> = safeCall {
        sourceLocal.getProductsWithDetailsById(productId)?.let { source ->
            val productImageList: List<ImageProduct> =
                imageSourceLocal.getImagesForProduct(productId).map { it.toDomain() }
            source.toDomain()
                .copy(imageProduct = productImageList)
        }
    }

    override suspend fun getStats(): SyncResult<SyncStats> {
        return try {
            val data = sourceLocal.getStats()
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun fetchRemote(): SyncResult<List<Product>> {
        return try {
            val data = sourceRemote.fetchCollection().map {
                it.toDomain()
            }
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun upsertLocal(register: Product): SyncResult<Unit> = safeCall {
        val productId = register.id.ifEmpty { generateUUID() }
        val entity = register.toEntity().copy(id = productId)

        val oldImageProduct: List<ImageProductEntity> = imageSourceLocal
            .getImagesForProduct(productId)
            .filter { old ->
                register.imageProduct.none { current -> current.filename == old.filename }
            }

        oldImageProduct.forEach {
            SharedImageStorage.deleteImage(it.toDomain().filename)
            imageSourceLocal.delete(it)
        }

        val newImages = register.imageProduct.filter { it.isTemp }
        val imageEntities: List<ImageProduct> = newImages.map { item ->
            val newPath = SharedImageStorage.saveImageFromTemp(item.filename)
            item.copy(filename = newPath, productId = productId, isTemp = false)
        }

        if (imageEntities.isNotEmpty()) {
            upsertImageProduct(imageEntities)
        }

        if (register.id.isEmpty()) {
            sourceLocal.save(entity)
        } else {
            sourceLocal.update(entity)
        }
    }


    override suspend fun upsertImageProduct(register: List<ImageProduct>): SyncResult<Unit> {
        return safeCall {
            val entities = register.map { it.toEntity() }
            entities.forEach {
                val isNew = it.id.isEmpty()
                if (isNew) {
                    val entity = it.copy(id = generateUUID())
                    imageSourceLocal.save(entity)
                } else {
                    imageSourceLocal.update(it)
                }
            }
        }
    }

    override suspend fun saveLocal(registers: List<Product>): SyncResult<Unit> {
        return safeCall {
            sourceLocal.save(registers.map { it.toEntity() })
        }
    }

    override suspend fun syncLocalToRemote(): SyncResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun syncRemoteToLocal(): SyncResult<Unit> {
        return safeCall {
            val localSyncedIds = sourceLocal.getSyncedIds()
            val remoteClients = sourceRemote.fetchCollection()
            sourceLocal.deleteUnsynced()
            val newClients = remoteClients.filterNot { it.id in localSyncedIds }
            sourceLocal.save(newClients.map {
                it.toEntity().copy(syncedDate = getCurrentTimestamp())
            })
        }
    }

    override suspend fun saveImageProductLocal(image: SharedImage): SyncResult<String> {
        return safeCall {
            SharedImageStorage.saveSharedImage(image)
        }
    }

}