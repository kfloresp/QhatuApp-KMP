package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.setting.domain.model.Brand
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.repository.BrandRepository
import com.rgk.qhatu.feature.setting.domain.repository.CategoryRepository

class GetBrandsUseCase(private val repository: BrandRepository) {
    suspend operator fun invoke(): SyncResult<List<Brand>> {
        return repository.fetchLocal()
    }
}
