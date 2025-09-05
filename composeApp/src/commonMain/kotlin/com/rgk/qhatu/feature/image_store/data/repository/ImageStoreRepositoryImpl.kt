package com.rgk.qhatu.feature.image_store.data.repository

import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.image_store.data.database.dao.ImageStoreDao
import com.rgk.qhatu.feature.image_store.domain.mapper.toDomain
import com.rgk.qhatu.feature.image_store.domain.mapper.toEntity
import com.rgk.qhatu.feature.image_store.domain.model.ImageStore
import com.rgk.qhatu.feature.image_store.domain.model.TableStore
import com.rgk.qhatu.feature.image_store.domain.repository.ImageStoreRepository
import com.rgk.qhatu.shared.SharedImage
import com.rgk.qhatu.shared.SharedImageStorage

class ImageStoreRepositoryImpl(private val sourceLocal: ImageStoreDao) : ImageStoreRepository {
    override suspend fun saveFileImageLocal(image: SharedImage): String {
        return SharedImageStorage.saveSharedImage(image)
    }

    override suspend fun deleteFileImageLocal(path: String) {
        SharedImageStorage.deleteImage(path)
    }

    override suspend fun upsertImageAllLocal(register: List<ImageStore>, type: TypeUpsert) {
        when (type) {
            TypeUpsert.NEW -> {
                sourceLocal.saveAll(register.map { it.toEntity() })
            }

            TypeUpsert.UPDATE -> {
                sourceLocal.updateAll(register.map { it.toEntity() })
            }

        }
    }

    override suspend fun deleteImageAllLocal(register: List<ImageStore>) {
        sourceLocal.deleteAll(register.map { it.toEntity() })
    }

    override suspend fun getImagesById(
        entityId: String,
        tableStore: TableStore,
    ): List<ImageStore> {
        val entries = sourceLocal.getImagesById(entityId, tableStore.name)
        return entries.map { it.toDomain() }
    }
}