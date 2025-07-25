package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.setting.domain.repository.StoreRepository

class GetStoreStatsUseCase(private val storeRepository: StoreRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return storeRepository.getStats()
    }
}