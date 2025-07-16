package com.rgk.qhatu.feature.payment.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.payment.domain.repository.PaymentTransactionRepository

class GetPaymentTransactionStatsUseCase(private val paymentTransactionRepository: PaymentTransactionRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return paymentTransactionRepository.getStats()
    }
}
