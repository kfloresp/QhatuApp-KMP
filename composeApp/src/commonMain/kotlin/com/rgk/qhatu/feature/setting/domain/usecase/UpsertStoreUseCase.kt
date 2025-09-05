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
        repositoryImageStore.upsertImageLocal(updatedImages)
    }
}