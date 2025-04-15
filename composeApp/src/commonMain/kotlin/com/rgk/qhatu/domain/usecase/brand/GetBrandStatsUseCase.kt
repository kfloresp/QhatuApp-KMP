package com.rgk.qhatu.domain.usecase.brand

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.repository.BrandRepository

class GetBrandStatsUseCase(private val brandRepository: BrandRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return brandRepository.getStats()
    }
}