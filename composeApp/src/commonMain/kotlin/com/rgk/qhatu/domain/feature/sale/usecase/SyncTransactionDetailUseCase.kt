package com.rgk.qhatu.domain.feature.sale.usecase

import com.rgk.qhatu.domain.common.SyncOperation
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.feature.sale.model.TransactionDetail
import com.rgk.qhatu.domain.feature.sale.repository.TransactionDetailRepository

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
