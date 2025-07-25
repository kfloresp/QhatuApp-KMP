package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Store
import com.rgk.qhatu.feature.setting.domain.repository.StoreRepository

class GetStoreUseCase(private val repository: StoreRepository) {
    suspend operator fun invoke(): SyncResult<Store> {
        return repository.fetchLocal()
    }
}
