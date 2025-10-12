package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.image_store.domain.model.TableStore
import com.rgk.qhatu.feature.image_store.domain.repository.ImageStoreRepository
import com.rgk.qhatu.feature.setting.domain.model.Store
import com.rgk.qhatu.feature.setting.domain.repository.StoreRepository

class GetStoreUseCase(
    private val repository: StoreRepository,
    private val repositoryImageStore: ImageStoreRepository,
) {
    suspend operator fun invoke(): SyncResult<Store> = safeCall {
        repository.fetchLocal()?.let { store ->
            val images = repositoryImageStore.getImagesById(store.id, TableStore.STORE)
            store.copy(images = images)
        } ?: Store()
    }
}