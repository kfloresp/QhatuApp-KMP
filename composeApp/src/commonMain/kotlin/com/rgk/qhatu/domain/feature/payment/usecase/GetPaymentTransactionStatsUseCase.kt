package com.rgk.qhatu.domain.feature.payment.usecase

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.feature.payment.repository.PaymentTransactionRepository

class GetPaymentTransactionStatsUseCase(private val paymentTransactionRepository: PaymentTransactionRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return paymentTransactionRepository.getStats()
    }
}
