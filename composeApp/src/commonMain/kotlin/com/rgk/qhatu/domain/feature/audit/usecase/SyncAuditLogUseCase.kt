package com.rgk.qhatu.domain.feature.audit.usecase

import com.rgk.qhatu.domain.common.SyncOperation
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.feature.audit.model.AuditLog
import com.rgk.qhatu.domain.feature.audit.repository.AuditLogRepository

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
