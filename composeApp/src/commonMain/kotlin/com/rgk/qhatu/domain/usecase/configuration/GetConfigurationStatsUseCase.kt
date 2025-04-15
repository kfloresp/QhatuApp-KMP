package com.rgk.qhatu.domain.usecase.configuration

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.repository.ConfigurationRepository

class GetConfigurationStatsUseCase(private val configurationRepository: ConfigurationRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return configurationRepository.getStats()
    }
}