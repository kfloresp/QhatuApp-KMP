package com.rgk.qhatu.domain.feature.payment.usecase

import com.rgk.qhatu.domain.common.SyncOperation
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.feature.payment.model.PaymentTransaction
import com.rgk.qhatu.domain.feature.payment.repository.PaymentTransactionRepository

class SyncPaymentTransactionUseCase(private val repository: PaymentTransactionRepository) {
    suspend operator fun invoke(operation: SyncOperation<PaymentTransaction>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.Save -> repository.saveLocal(operation.registers)
            is SyncOperation.Update -> repository.updateLocal(operation.register)
            is SyncOperation.Upload -> repository.syncLocalToRemote()
            is SyncOperation.Download -> repository.syncRemoteToLocal()
        }
    }
}
