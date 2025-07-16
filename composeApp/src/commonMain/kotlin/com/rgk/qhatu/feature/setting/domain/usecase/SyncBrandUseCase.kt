package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Brand
import com.rgk.qhatu.feature.setting.domain.repository.BrandRepository

class SyncBrandUseCase(private val repository: BrandRepository) {
    suspend operator fun invoke(operation: SyncOperation<Brand>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.Save -> repository.saveLocal(operation.registers)
            is SyncOperation.Update -> repository.updateLocal(operation.register)
            is SyncOperation.Upload -> repository.syncLocalToRemote()
            is SyncOperation.Download -> repository.syncRemoteToLocal()
        }
    }
}
