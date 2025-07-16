package com.rgk.qhatu.feature.audit.domain.usecase

import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.audit.domain.model.AuditLog
import com.rgk.qhatu.feature.audit.domain.repository.AuditLogRepository

class SyncAuditLogUseCase(private val repository: AuditLogRepository) {
    suspend operator fun invoke(operation: SyncOperation<AuditLog>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.Save -> repository.saveLocal(operation.registers)
            is SyncOperation.Update -> repository.updateLocal(operation.register)
            is SyncOperation.Upload -> repository.syncLocalToRemote()
            is SyncOperation.Download -> repository.syncRemoteToLocal()
        }
    }
}
