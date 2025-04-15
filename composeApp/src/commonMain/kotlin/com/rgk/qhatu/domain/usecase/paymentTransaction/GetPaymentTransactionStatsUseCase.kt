package com.rgk.qhatu.domain.usecase.paymentTransaction

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.repository.PaymentTransactionRepository

class GetPaymentTransactionStatsUseCase(private val paymentTransactionRepository: PaymentTransactionRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return paymentTransactionRepository.getStats()
    }
}
