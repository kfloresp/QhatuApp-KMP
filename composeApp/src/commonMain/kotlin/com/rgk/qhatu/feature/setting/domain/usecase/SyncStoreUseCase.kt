package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Store
import com.rgk.qhatu.feature.setting.domain.repository.StoreRepository

class SyncStoreUseCase(private val repository: StoreRepository) {
    suspend operator fun invoke(operation: SyncOperation<Store>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.Save -> {
                repository.saveLocal(operation.registers)
            }
            is SyncOperation.Update -> {
                repository.uploadRemote(operation.register)
            }
            is SyncOperation.Download -> {
                repository.syncRemoteToLocal()
            }

            is SyncOperation.Upload<*> -> {
                repository.syncRemoteToLocal()
            }
        }
    }
}