package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.image_store.domain.repository.ImageStoreRepository
import com.rgk.qhatu.feature.setting.domain.model.Store
import com.rgk.qhatu.feature.setting.domain.repository.StoreRepository

class UpsertStoreUseCase(
    private val repository: StoreRepository,
    private val repositoryImageStore: ImageStoreRepository,
) {
    suspend operator fun invoke(store: Store): SyncResult<Unit> = safeCall {
        val storeId = store.id.ifEmpty { generateUUID() }
        val type = if (store.id.isEmpty()) {
            TypeUpsert.NEW
        } else {
            TypeUpsert.UPDATE
        }
        val updatedStore = store.copy(id = storeId)
        val updatedImages = store.images.map { it.copy(entityId = storeId) }

        repository.upsertLocal(updatedStore, type)
        repositoryImageStore.upsertImageAllLocal(updatedImages,type)
    }
}

/* val productId = register.id.ifEmpty { generateUUID() }
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
        }*/