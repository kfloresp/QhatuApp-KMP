package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.repository.CategoryRepository

class GetCategoriesUseCase(private val repository: CategoryRepository) {
    suspend operator fun invoke(): SyncResult<List<Category>> = safeCall {
        repository.fetchLocal()
    }
}
