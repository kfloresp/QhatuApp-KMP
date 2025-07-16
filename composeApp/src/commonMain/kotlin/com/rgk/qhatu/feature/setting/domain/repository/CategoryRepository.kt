package com.rgk.qhatu.feature.setting.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.setting.domain.model.Category

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