package com.rgk.qhatu.domain.usecase.client

import com.rgk.qhatu.domain.common.SyncOperation
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.model.AuditLog
import com.rgk.qhatu.domain.model.Client
import com.rgk.qhatu.domain.repository.AuditLogRepository
import com.rgk.qhatu.domain.repository.ClientRepository

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
