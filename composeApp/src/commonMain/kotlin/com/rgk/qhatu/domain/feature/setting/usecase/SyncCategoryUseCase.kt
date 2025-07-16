package com.rgk.qhatu.domain.feature.setting.usecase

import com.rgk.qhatu.domain.common.SyncOperation
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.feature.setting.model.Category
import com.rgk.qhatu.domain.feature.setting.repository.CategoryRepository

class SyncCategoryUseCase(private val repository: CategoryRepository) {
    suspend operator fun invoke(operation: SyncOperation<Category>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.Save -> repository.saveLocal(operation.registers)
            is SyncOperation.Update -> repository.updateLocal(operation.register)
            is SyncOperation.Upload -> repository.syncLocalToRemote()
            is SyncOperation.Download -> repository.syncRemoteToLocal()
        }
    }
}
