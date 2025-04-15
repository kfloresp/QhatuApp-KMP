package com.rgk.qhatu.domain.usecase.transaction

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.repository.TransactionRepository

class GetTransactionStatsUseCase(private val transactionRepository: TransactionRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return transactionRepository.getStats()
    }
}
