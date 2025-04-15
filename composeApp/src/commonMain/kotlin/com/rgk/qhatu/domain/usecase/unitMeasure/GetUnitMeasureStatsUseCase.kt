package com.rgk.qhatu.domain.usecase.unitMeasure

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.repository.UnitMeasureRepository

class GetUnitMeasureStatsUseCase(private val unitMeasureRepository: UnitMeasureRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return unitMeasureRepository.getStats()
    }
}
