package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.setting.domain.repository.BrandRepository

class GetBrandStatsUseCase(private val brandRepository: BrandRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return brandRepository.getStats()
    }
}