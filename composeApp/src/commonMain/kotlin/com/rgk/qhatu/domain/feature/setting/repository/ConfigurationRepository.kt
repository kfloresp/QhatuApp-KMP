package com.rgk.qhatu.domain.feature.setting.repository

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.feature.setting.model.Configuration

interface ConfigurationRepository {
    suspend fun fetchLocal(): SyncResult<List<Configuration>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<Configuration>>
    suspend fun uploadRemote() : SyncResult<Boolean>
    suspend fun updateLocal(register: Configuration): SyncResult<Boolean>
    suspend fun saveLocal(registers: List<Configuration>) : SyncResult<Boolean>
    suspend fun syncLocalToRemote(): SyncResult<Boolean>
    suspend fun syncRemoteToLocal(): SyncResult<Boolean>
}