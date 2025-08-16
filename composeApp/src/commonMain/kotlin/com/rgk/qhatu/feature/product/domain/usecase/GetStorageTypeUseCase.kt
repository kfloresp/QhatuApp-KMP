package com.rgk.qhatu.feature.product.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Configuration
import com.rgk.qhatu.feature.setting.domain.repository.ConfigurationRepository

private const val PARAM = "tipo_almacenamiento"

class GetStorageTypeUseCase(private val repository: ConfigurationRepository) {
    suspend operator fun invoke(): SyncResult<List<Configuration>> {
        return repository.fetchLocal(PARAM)
    }
}