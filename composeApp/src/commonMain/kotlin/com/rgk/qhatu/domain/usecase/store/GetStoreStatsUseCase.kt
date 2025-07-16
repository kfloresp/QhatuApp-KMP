package com.rgk.qhatu.domain.usecase.store

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.repository.StoreRepository

class GetStoreStatsUseCase(private val storeRepository: StoreRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return storeRepository.getStats()
    }
}