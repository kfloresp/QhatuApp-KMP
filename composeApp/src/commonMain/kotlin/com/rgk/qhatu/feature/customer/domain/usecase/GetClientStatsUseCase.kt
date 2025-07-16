package com.rgk.qhatu.feature.customer.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.customer.domain.repository.ClientRepository

class GetClientStatsUseCase(private val clientRepository: ClientRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return clientRepository.getStats()
    }
}