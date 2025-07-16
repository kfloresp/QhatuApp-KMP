package com.rgk.qhatu.domain.feature.customer.usecase

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.feature.customer.repository.ClientRepository

class GetClientStatsUseCase(private val clientRepository: ClientRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return clientRepository.getStats()
    }
}