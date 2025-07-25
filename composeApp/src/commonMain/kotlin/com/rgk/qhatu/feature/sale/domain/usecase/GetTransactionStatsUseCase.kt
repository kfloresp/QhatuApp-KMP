package com.rgk.qhatu.feature.sale.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.sale.domain.repository.TransactionRepository

class GetTransactionStatsUseCase(private val transactionRepository: TransactionRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return transactionRepository.getStats()
    }
}
