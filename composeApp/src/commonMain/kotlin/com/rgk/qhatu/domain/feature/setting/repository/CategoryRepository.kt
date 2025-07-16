package com.rgk.qhatu.domain.feature.setting.repository

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.feature.setting.model.Category

interface CategoryRepository {
    suspend fun fetchLocal(): SyncResult<List<Category>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<Category>>
    suspend fun uploadRemote() : SyncResult<Boolean>
    suspend fun updateLocal(register: Category): SyncResult<Boolean>
    suspend fun saveLocal(registers: List<Category>) : SyncResult<Boolean>
    suspend fun syncLocalToRemote(): SyncResult<Boolean>
    suspend fun syncRemoteToLocal(): SyncResult<Boolean>

}