package com.rgk.qhatu.domain.usecase.configuration

import com.rgk.qhatu.domain.common.SyncOperation
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.model.AuditLog
import com.rgk.qhatu.domain.model.Configuration
import com.rgk.qhatu.domain.repository.AuditLogRepository
import com.rgk.qhatu.domain.repository.ConfigurationRepository

class SyncConfigurationUseCase(private val repository: ConfigurationRepository) {
    suspend operator fun invoke(operation: SyncOperation<Configuration>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.Save -> repository.saveLocal(operation.registers)
            is SyncOperation.Update -> repository.updateLocal(operation.register)
            is SyncOperation.Upload -> repository.syncLocalToRemote()
            is SyncOperation.Download -> repository.syncRemoteToLocal()
        }
    }
}
