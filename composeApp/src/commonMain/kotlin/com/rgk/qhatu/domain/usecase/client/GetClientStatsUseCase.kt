package com.rgk.qhatu.domain.usecase.client

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.repository.ClientRepository

class GetClientStatsUseCase(private val clientRepository: ClientRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return clientRepository.getStats()
    }
}