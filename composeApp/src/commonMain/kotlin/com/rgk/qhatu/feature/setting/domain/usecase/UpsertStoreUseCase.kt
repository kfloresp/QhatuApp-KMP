package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.image_store.domain.model.TableStore
import com.rgk.qhatu.feature.image_store.domain.usecase.SyncImageStoreUseCase
import com.rgk.qhatu.feature.setting.domain.model.Store
import com.rgk.qhatu.feature.setting.domain.repository.StoreRepository

class UpsertStoreUseCase(
    private val repository: StoreRepository,
    private val syncImageStoreUseCase: SyncImageStoreUseCase,
) {
    suspend operator fun invoke(store: Store): SyncResult<Unit> = safeCall {
        val storeId = store.id.ifEmpty { generateUUID() }
        val type = if (store.id.isEmpty()) {
            TypeUpsert.NEW
        } else {
            TypeUpsert.UPDATE
        }
        val updatedStore = store.copy(id = storeId)
        repository.upsertLocal(updatedStore, type)
        syncImageStoreUseCase(
            entityId = storeId,
            tableStore = TableStore.STORE,
            images = store.images
        )
    }
}