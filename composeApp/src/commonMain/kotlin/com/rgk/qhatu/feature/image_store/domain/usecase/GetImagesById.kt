package com.rgk.qhatu.feature.image_store.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.image_store.domain.model.ImageStore
import com.rgk.qhatu.feature.image_store.domain.model.TableStore
import com.rgk.qhatu.feature.image_store.domain.repository.ImageStoreRepository

class GetImagesById(private val repository: ImageStoreRepository) {
    suspend operator fun invoke(
        entityId: String,
        tableStore: TableStore,
    ): SyncResult<List<ImageStore>> {
        return repository.getImagesById(entityId, tableStore)
    }
}
