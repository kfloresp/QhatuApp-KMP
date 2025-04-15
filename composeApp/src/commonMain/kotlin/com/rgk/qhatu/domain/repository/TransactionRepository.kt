package com.rgk.qhatu.domain.repository

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.model.Transaction

interface TransactionRepository {
    suspend fun fetchLocal(): SyncResult<List<Transaction>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<Transaction>>
    suspend fun uploadRemote() : SyncResult<Boolean>
    suspend fun updateLocal(register: Transaction): SyncResult<Boolean>
    suspend fun saveLocal(registers: List<Transaction>) : SyncResult<Boolean>
    suspend fun syncLocalToRemote(): SyncResult<Boolean>
    suspend fun syncRemoteToLocal(): SyncResult<Boolean>

}