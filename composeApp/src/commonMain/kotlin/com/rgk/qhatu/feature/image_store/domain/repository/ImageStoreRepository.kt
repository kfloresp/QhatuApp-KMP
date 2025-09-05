package com.rgk.qhatu.feature.image_store.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.image_store.domain.model.ImageStore
import com.rgk.qhatu.feature.image_store.domain.model.TableStore
import com.rgk.qhatu.shared.SharedImage

interface ImageStoreRepository {
    suspend fun saveFileImageLocal(image: SharedImage): SyncResult<String>
    suspend fun deleteFileImageLocal(path: String): SyncResult<Unit>
    suspend fun upsertImageLocal(register: List<ImageStore>)
    suspend fun getImagesById(
        entityId: String,
        tableStore: TableStore,
    ): List<ImageStore>
}