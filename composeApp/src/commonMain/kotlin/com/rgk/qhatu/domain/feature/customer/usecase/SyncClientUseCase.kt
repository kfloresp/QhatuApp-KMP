package com.rgk.qhatu.domain.feature.customer.usecase

import com.rgk.qhatu.domain.common.SyncOperation
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.feature.customer.model.Client
import com.rgk.qhatu.domain.feature.customer.repository.ClientRepository

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
