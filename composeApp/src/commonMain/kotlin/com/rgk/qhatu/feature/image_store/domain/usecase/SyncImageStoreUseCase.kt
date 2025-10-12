package com.rgk.qhatu.feature.image_store.domain.usecase

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.image_store.domain.model.ImageStore
import com.rgk.qhatu.feature.image_store.domain.model.TableStore
import com.rgk.qhatu.feature.image_store.domain.repository.ImageStoreRepository

class SyncImageStoreUseCase(private val repository: ImageStoreRepository) {
    suspend operator fun invoke(
        entityId: String,
        tableStore: TableStore,
        images: List<ImageStore>,
    ): SyncResult<Unit> =
        safeCall {
            val oldImages = repository.getImagesById(entityId, tableStore)

            val removedImages = oldImages.filter { old ->
                images.none { current -> current.filename == old.filename }
            }

            removedImages.forEach {
                repository.deleteFileImageLocal(it.filename)
                repository.deleteImageByPath(it.filename)
            }

            val newImages = images.filter { it.isTemp }

            val processedNewImages = newImages.map { item ->
                val newPath = repository.saveFileImageFromTemp(item.filename)
                item.copy(
                    id = generateUUID(),
                    filename = newPath,
                    entityId = entityId,
                    isTemp = false
                )
            }

            if (processedNewImages.isNotEmpty()) {
                repository.upsertImageAllLocal(processedNewImages, TypeUpsert.NEW)
            }
        }
}