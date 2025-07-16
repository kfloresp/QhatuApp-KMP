package com.rgk.qhatu.feature.sale.domain.usecase

import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.sale.domain.model.TransactionDetail
import com.rgk.qhatu.feature.sale.domain.repository.TransactionDetailRepository

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
