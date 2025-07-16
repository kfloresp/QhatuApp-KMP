package com.rgk.qhatu.domain.feature.sale.usecase

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.feature.sale.repository.TransactionDetailRepository

class GetTransactionDetailStatsUseCase(private val transactionDetailRepository: TransactionDetailRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return transactionDetailRepository.getStats()
    }
}
