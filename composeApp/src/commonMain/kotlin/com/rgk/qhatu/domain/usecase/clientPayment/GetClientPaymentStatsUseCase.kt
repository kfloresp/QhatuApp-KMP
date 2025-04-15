package com.rgk.qhatu.domain.usecase.clientPayment

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.repository.ClientPaymentRepository

class GetClientPaymentStatsUseCase(private val clientPaymentRepository: ClientPaymentRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return clientPaymentRepository.getStats()
    }
}