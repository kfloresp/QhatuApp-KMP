package com.rgk.qhatu.domain.usecase.transaction

import com.rgk.qhatu.domain.common.SyncOperation
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.model.AuditLog
import com.rgk.qhatu.domain.model.Transaction
import com.rgk.qhatu.domain.repository.AuditLogRepository
import com.rgk.qhatu.domain.repository.TransactionRepository

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
