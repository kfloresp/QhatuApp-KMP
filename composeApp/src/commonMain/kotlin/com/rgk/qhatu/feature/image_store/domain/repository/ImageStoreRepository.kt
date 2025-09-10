package com.rgk.qhatu.feature.image_store.domain.repository

import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.image_store.domain.model.ImageStore
import com.rgk.qhatu.feature.image_store.domain.model.TableStore
import com.rgk.qhatu.shared.SharedImage

interface ImageStoreRepository {
    suspend fun saveFileImageLocal(image: SharedImage): String
    suspend fun saveFileImageFromTemp(path: String): String
    suspend fun deleteFileImageLocal(path: String)
    suspend fun deleteImageByPath(path: String)
    suspend fun upsertImageAllLocal(register: List<ImageStore>, type: TypeUpsert)
    suspend fun getImagesById(
        entityId: String,
        tableStore: TableStore,
    ): List<ImageStore>
}