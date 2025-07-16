package com.rgk.qhatu.feature.payment.domain.usecase

import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.payment.domain.model.ClientPayment
import com.rgk.qhatu.feature.payment.domain.repository.ClientPaymentRepository

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
