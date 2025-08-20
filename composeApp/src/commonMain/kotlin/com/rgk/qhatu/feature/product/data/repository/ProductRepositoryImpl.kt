package com.rgk.qhatu.feature.product.data.repository

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.feature.product.data.database.dao.ProductDao
import com.rgk.qhatu.feature.product.data.remote.ProductRemoteDataSource
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.feature.product.data.database.dao.ImageProductDao
import com.rgk.qhatu.feature.product.domain.mapper.toDomain
import com.rgk.qhatu.feature.product.domain.mapper.toEntity
import com.rgk.qhatu.feature.product.domain.model.ImageProduct
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.domain.repository.ProductRepository
import com.rgk.qhatu.utils.TimeUtils

class ProductRepositoryImpl(
    private val sourceRemote: ProductRemoteDataSource,
    private val sourceLocal: ProductDao,
    private val imageSourceLocal: ImageProductDao,
) : ProductRepository {
    override suspend fun fetchLocal(
        productId: String?,
    ): SyncResult<List<Product>> {
        return try {
            var data: List<Product> = emptyList()
            productId?.let {
                data = sourceLocal.getProductsWithDetailsById(productId).map {
                    val result = fetchImageProduct(it.id)
                    var productImageList: List<ImageProduct> = emptyList()
                    when (result) {
                        is SyncResult.Error -> {
                            SyncResult.Error(result.exception)
                        }

                        is SyncResult.Success<List<ImageProduct>> -> {
                            productImageList = result.data
                        }
                    }
                    it.toDomain().copy(imageProduct = productImageList)
                }
            } ?: run {
                data = sourceLocal.getProductsWithDetails().map {
                    val result = fetchImageProduct(it.id)
                    var productImageList: List<ImageProduct> = emptyList()
                    when (result) {
                        is SyncResult.Error -> {
                            SyncResult.Error(result.exception)
                        }

                        is SyncResult.Success<List<ImageProduct>> -> {
                            productImageList = result.data
                        }
                    }
                    it.toDomain().copy(imageProduct = productImageList)
                }
            }
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
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

    override suspend fun upsertLocal(register: Product): SyncResult<String> = safeCall {
        val productId = register.id.ifEmpty { generateUUID() }
        val entity = register.toEntity().copy(id = productId)

        if (register.id.isEmpty()) {
            sourceLocal.save(entity)
        } else {
            sourceLocal.update(entity)
        }
        productId
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

    override suspend fun deleteImageProduct(register: List<ImageProduct>): SyncResult<Unit> {
        return safeCall {
            val entities = register.map { it.toEntity() }
            imageSourceLocal.deleteAll(entities)
        }
    }

    override suspend fun fetchImageProduct(
        productId: String,
    ): SyncResult<List<ImageProduct>> {
        return try {
            val data: List<ImageProduct> =
                imageSourceLocal.getImagesForProduct(productId).map { it.toDomain() }
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
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
                it.toEntity().copy(syncedDate = TimeUtils.getCurrentTimestamp())
            })
        }
    }

}