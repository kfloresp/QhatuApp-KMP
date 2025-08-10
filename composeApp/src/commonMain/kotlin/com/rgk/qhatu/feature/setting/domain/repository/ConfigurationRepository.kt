package com.rgk.qhatu.feature.setting.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.setting.domain.model.Configuration

interface ConfigurationRepository {
    suspend fun fetchLocal(param: String?): SyncResult<List<Configuration>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<Configuration>>
    suspend fun saveLocal(registers: List<Configuration>): SyncResult<Unit>
    suspend fun syncLocalToRemote(): SyncResult<Unit>
    suspend fun syncRemoteToLocal(): SyncResult<Unit>
}