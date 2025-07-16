package com.rgk.qhatu.domain.feature.payment.usecase

import com.rgk.qhatu.domain.common.SyncOperation
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.feature.payment.model.ClientPayment
import com.rgk.qhatu.domain.feature.payment.repository.ClientPaymentRepository

class SyncClientPaymentUseCase(private val repository: ClientPaymentRepository) {
    suspend operator fun invoke(operation: SyncOperation<ClientPayment>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.Save -> repository.saveLocal(operation.registers)
            is SyncOperation.Update -> repository.updateLocal(operation.register)
            is SyncOperation.Upload -> repository.syncLocalToRemote()
            is SyncOperation.Download -> repository.syncRemoteToLocal()
        }
    }
}
