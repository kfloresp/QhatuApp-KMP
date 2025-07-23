package com.rgk.qhatu.feature.setting.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.setting.domain.model.Configuration

interface ConfigurationRepository {
    suspend fun fetchLocal(): SyncResult<List<Configuration>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<Configuration>>
    suspend fun uploadRemote() : SyncResult<Boolean>
    suspend fun saveLocal(registers: List<Configuration>) : SyncResult<Boolean>
    suspend fun syncLocalToRemote(): SyncResult<Boolean>
    suspend fun syncRemoteToLocal(): SyncResult<Boolean>
}