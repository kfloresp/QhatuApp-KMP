package com.rgk.qhatu.feature.setting.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.setting.domain.model.Brand

interface BrandRepository {
    suspend fun fetchLocal(): SyncResult<List<Brand>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<Brand>>
    suspend fun uploadRemote() : SyncResult<Boolean>
    suspend fun updateLocal(register: Brand): SyncResult<Boolean>
    suspend fun saveLocal(registers: List<Brand>) : SyncResult<Boolean>
    suspend fun syncLocalToRemote(): SyncResult<Boolean>
    suspend fun syncRemoteToLocal(): SyncResult<Boolean>
}