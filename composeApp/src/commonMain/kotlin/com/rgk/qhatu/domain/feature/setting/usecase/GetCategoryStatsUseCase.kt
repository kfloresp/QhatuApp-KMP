package com.rgk.qhatu.domain.feature.setting.usecase

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.feature.setting.repository.CategoryRepository

class GetCategoryStatsUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return categoryRepository.getStats()
    }
}
