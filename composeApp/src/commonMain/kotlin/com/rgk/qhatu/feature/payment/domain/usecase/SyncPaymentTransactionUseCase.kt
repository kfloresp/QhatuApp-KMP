package com.rgk.qhatu.feature.payment.domain.usecase

import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.payment.domain.model.PaymentTransaction
import com.rgk.qhatu.feature.payment.domain.repository.PaymentTransactionRepository

class SyncPaymentTransactionUseCase(private val repository: PaymentTransactionRepository) {
    suspend operator fun invoke(operation: SyncOperation<PaymentTransaction>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.SaveLocal -> repository.saveLocal(operation.registers)
            is SyncOperation.UpsertLocal -> repository.updateLocal(operation.register)
            is SyncOperation.LocalToRemote -> repository.syncLocalToRemote()
            is SyncOperation.RemoteToLocal -> repository.syncRemoteToLocal()
        }
    }
}
