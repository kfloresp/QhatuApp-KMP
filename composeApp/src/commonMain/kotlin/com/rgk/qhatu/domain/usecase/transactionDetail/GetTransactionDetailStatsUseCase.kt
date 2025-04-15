package com.rgk.qhatu.domain.usecase.transactionDetail

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.repository.TransactionDetailRepository

class GetTransactionDetailStatsUseCase(private val transactionDetailRepository: TransactionDetailRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return transactionDetailRepository.getStats()
    }
}
