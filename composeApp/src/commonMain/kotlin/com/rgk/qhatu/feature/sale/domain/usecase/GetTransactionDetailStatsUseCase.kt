package com.rgk.qhatu.feature.sale.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.sale.domain.repository.TransactionDetailRepository

class GetTransactionDetailStatsUseCase(private val transactionDetailRepository: TransactionDetailRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return transactionDetailRepository.getStats()
    }
}
