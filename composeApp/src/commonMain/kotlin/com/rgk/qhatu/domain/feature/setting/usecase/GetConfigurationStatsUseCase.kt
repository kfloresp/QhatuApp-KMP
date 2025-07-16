package com.rgk.qhatu.domain.feature.setting.usecase

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.feature.setting.repository.ConfigurationRepository

class GetConfigurationStatsUseCase(private val configurationRepository: ConfigurationRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return configurationRepository.getStats()
    }
}