package com.rgk.qhatu.domain.feature.setting.usecase

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.feature.setting.repository.UnitMeasureRepository

class GetUnitMeasureStatsUseCase(private val unitMeasureRepository: UnitMeasureRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return unitMeasureRepository.getStats()
    }
}
