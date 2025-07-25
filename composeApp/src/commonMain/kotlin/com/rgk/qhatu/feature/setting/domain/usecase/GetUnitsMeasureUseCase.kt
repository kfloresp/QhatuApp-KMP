package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.setting.domain.model.Brand
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import com.rgk.qhatu.feature.setting.domain.repository.BrandRepository
import com.rgk.qhatu.feature.setting.domain.repository.CategoryRepository
import com.rgk.qhatu.feature.setting.domain.repository.UnitMeasureRepository

class GetUnitsMeasureUseCase(private val repository: UnitMeasureRepository) {
    suspend operator fun invoke(): SyncResult<List<UnitMeasure>> {
        return repository.fetchLocal()
    }
}
