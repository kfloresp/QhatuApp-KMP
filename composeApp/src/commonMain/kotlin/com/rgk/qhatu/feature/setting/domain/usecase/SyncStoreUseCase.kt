package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Store
import com.rgk.qhatu.feature.setting.domain.repository.StoreRepository

class SyncStoreUseCase(private val repository: StoreRepository) {
    suspend operator fun invoke(operation: SyncOperation<Store>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.SaveLocal -> {
                repository.saveLocal(operation.registers)
            }
            is SyncOperation.UpsertLocal -> {
                repository.upsertLocal(operation.register)
            }
            is SyncOperation.LocalToRemote -> {
                repository.syncRemoteToLocal()
            }
            is SyncOperation.RemoteToLocal<*> -> {
                repository.syncRemoteToLocal()
            }
        }
    }
}