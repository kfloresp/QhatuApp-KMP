package com.rgk.qhatu.domain.feature.setting.usecase

import com.rgk.qhatu.domain.common.SyncOperation
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.feature.setting.model.Brand
import com.rgk.qhatu.domain.feature.setting.repository.BrandRepository

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
