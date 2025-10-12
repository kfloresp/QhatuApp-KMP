package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import com.rgk.qhatu.feature.setting.domain.repository.UnitMeasureRepository

class GetUnitsMeasureUseCase(private val repository: UnitMeasureRepository) {
    suspend operator fun invoke(): SyncResult<List<UnitMeasure>> = safeCall {
        repository.fetchLocal()
    }
}
