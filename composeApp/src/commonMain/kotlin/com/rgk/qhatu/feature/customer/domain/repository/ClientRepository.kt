package com.rgk.qhatu.feature.customer.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.customer.domain.model.Client

interface ClientRepository {
    suspend fun fetchLocal(): SyncResult<List<Client>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<Client>>
    suspend fun uploadRemote() : SyncResult<Boolean>
    suspend fun updateLocal(register: Client): SyncResult<Boolean>
    suspend fun saveLocal(registers: List<Client>) : SyncResult<Boolean>
    suspend fun syncLocalToRemote(): SyncResult<Boolean>
    suspend fun syncRemoteToLocal(): SyncResult<Boolean>
    suspend fun fetchClient(query:String): SyncResult<List<Client>>
    suspend fun fetchProvider(query:String): SyncResult<List<Client>>
}