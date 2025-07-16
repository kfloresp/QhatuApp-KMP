package com.rgk.qhatu.domain.usecase.store

import com.rgk.qhatu.domain.common.SyncOperation
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.model.Store
import com.rgk.qhatu.domain.repository.StoreRepository

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