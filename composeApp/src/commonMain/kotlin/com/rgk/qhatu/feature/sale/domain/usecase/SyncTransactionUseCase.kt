package com.rgk.qhatu.feature.sale.domain.usecase

import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.sale.domain.model.Transaction
import com.rgk.qhatu.feature.sale.domain.repository.TransactionRepository

class SyncTransactionUseCase(private val repository: TransactionRepository) {
    suspend operator fun invoke(operation: SyncOperation<Transaction>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.Save -> repository.saveLocal(operation.registers)
            is SyncOperation.Update -> repository.updateLocal(operation.register)
            is SyncOperation.Upload -> repository.syncLocalToRemote()
            is SyncOperation.Download -> repository.syncRemoteToLocal()
        }
    }
}
