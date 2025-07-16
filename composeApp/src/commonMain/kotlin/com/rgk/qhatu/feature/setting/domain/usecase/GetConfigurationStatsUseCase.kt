package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.setting.domain.repository.ConfigurationRepository

class GetConfigurationStatsUseCase(private val configurationRepository: ConfigurationRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return configurationRepository.getStats()
    }
}