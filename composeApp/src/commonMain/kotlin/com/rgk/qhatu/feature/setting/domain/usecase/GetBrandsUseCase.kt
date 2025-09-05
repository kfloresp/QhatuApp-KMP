package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Brand
import com.rgk.qhatu.feature.setting.domain.repository.BrandRepository

class GetBrandsUseCase(private val repository: BrandRepository) {
    suspend operator fun invoke(): SyncResult<List<Brand>> = safeCall {
        repository.fetchLocal()
    }
}
