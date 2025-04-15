package com.rgk.qhatu.domain.repository

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.model.Category
import kotlinx.coroutines.flow.Flow

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