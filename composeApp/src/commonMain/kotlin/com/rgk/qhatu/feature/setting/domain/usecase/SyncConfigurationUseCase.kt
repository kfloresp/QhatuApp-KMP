package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Configuration
import com.rgk.qhatu.feature.setting.domain.repository.ConfigurationRepository

class SyncConfigurationUseCase(private val repository: ConfigurationRepository) {
    suspend operator fun invoke(operation: SyncOperation<Configuration>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.SaveLocal -> repository.saveLocal(operation.registers)
            is SyncOperation.LocalToRemote -> repository.syncLocalToRemote()
            else -> repository.syncRemoteToLocal()
        }
    }
}
