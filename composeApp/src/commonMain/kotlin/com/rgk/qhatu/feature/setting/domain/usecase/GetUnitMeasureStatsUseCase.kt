package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.setting.domain.repository.UnitMeasureRepository

class GetUnitMeasureStatsUseCase(private val unitMeasureRepository: UnitMeasureRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return unitMeasureRepository.getStats()
    }
}
