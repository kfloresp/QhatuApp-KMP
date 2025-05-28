package com.rgk.qhatu.domain.repository

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.model.Client

interface ClientRepository {
    suspend fun fetchLocal(): SyncResult<List<Client>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<Client>>
    suspend fun uploadRemote() : SyncResult<Boolean>
    suspend fun updateLocal(register: Client): SyncResult<Boolean>
    suspend fun saveLocal(registers: List<Client>) : SyncResult<Boolean>
    suspend fun syncLocalToRemote(): SyncResult<Boolean>
    suspend fun syncRemoteToLocal(): SyncResult<Boolean>
    suspend fun fetchClientProvider(query:String, isProvider:Int): SyncResult<List<Client>>
}