package com.rgk.qhatu.domain.usecase.transactionDetail

import com.rgk.qhatu.domain.common.SyncOperation
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.model.AuditLog
import com.rgk.qhatu.domain.model.TransactionDetail
import com.rgk.qhatu.domain.repository.AuditLogRepository
import com.rgk.qhatu.domain.repository.TransactionDetailRepository

class SyncTransactionDetailUseCase(private val repository: TransactionDetailRepository) {
    suspend operator fun invoke(operation: SyncOperation<TransactionDetail>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.Save -> repository.saveLocal(operation.registers)
            is SyncOperation.Update -> repository.updateLocal(operation.register)
            is SyncOperation.Upload -> repository.syncLocalToRemote()
            is SyncOperation.Download -> repository.syncRemoteToLocal()
        }
    }
}
