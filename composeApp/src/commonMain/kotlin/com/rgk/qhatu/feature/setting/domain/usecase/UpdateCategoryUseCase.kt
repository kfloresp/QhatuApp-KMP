package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.repository.CategoryRepository

class UpdateCategoryUseCase(private val repository: CategoryRepository) {
    suspend operator fun invoke(register: Category): SyncResult<Unit>{
        return repository.updateLocal(register = register)
    }
}
