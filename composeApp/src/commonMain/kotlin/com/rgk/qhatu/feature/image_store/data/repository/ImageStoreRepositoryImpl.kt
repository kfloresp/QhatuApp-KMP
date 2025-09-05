package com.rgk.qhatu.feature.image_store.data.repository

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.feature.image_store.data.database.dao.ImageStoreDao
import com.rgk.qhatu.feature.image_store.domain.mapper.toDomain
import com.rgk.qhatu.feature.image_store.domain.mapper.toEntity
import com.rgk.qhatu.feature.image_store.domain.model.ImageStore
import com.rgk.qhatu.feature.image_store.domain.model.TableStore
import com.rgk.qhatu.feature.image_store.domain.repository.ImageStoreRepository
import com.rgk.qhatu.shared.SharedImage
import com.rgk.qhatu.shared.SharedImageStorage

class ImageStoreRepositoryImpl(private val sourceLocal: ImageStoreDao) : ImageStoreRepository {
    override suspend fun saveFileImageLocal(image: SharedImage): SyncResult<String> = safeCall {
        SharedImageStorage.saveSharedImage(image)
    }

    override suspend fun deleteFileImageLocal(path: String): SyncResult<Unit> = safeCall {
        SharedImageStorage.deleteImage(path)
    }

    override suspend fun upsertImageLocal(register: List<ImageStore>) {
        val entities = register.map { it.toEntity() }
        entities.forEach {
            val isNew = it.id.isEmpty()
            if (isNew) {
                val entity = it.copy(id = generateUUID())
                sourceLocal.save(entity)
            } else {
                sourceLocal.update(it)
            }
        }
    }

    override suspend fun getImagesById(
        entityId: String,
        tableStore: TableStore,
    ): List<ImageStore> {
        val entries = sourceLocal.getImagesById(entityId, tableStore.name)
        return entries.map { it.toDomain() }
    }
}