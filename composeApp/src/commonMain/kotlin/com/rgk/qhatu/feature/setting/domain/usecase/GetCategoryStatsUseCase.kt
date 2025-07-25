package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.setting.domain.repository.CategoryRepository

class GetCategoryStatsUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return categoryRepository.getStats()
    }
}
