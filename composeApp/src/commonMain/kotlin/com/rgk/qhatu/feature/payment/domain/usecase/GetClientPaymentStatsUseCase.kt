package com.rgk.qhatu.feature.payment.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.payment.domain.repository.ClientPaymentRepository

class GetClientPaymentStatsUseCase(private val clientPaymentRepository: ClientPaymentRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return clientPaymentRepository.getStats()
    }
}