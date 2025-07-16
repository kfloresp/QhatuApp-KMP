package com.rgk.qhatu.feature.customer.domain.usecase

import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.Client
import com.rgk.qhatu.feature.customer.domain.repository.ClientRepository

class SyncClientUseCase(private val repository: ClientRepository) {
    suspend operator fun invoke(operation: SyncOperation<Client>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.Save -> repository.saveLocal(operation.registers)
            is SyncOperation.Update -> repository.updateLocal(operation.register)
            is SyncOperation.Upload -> repository.syncLocalToRemote()
            is SyncOperation.Download -> repository.syncRemoteToLocal()
        }
    }
}
