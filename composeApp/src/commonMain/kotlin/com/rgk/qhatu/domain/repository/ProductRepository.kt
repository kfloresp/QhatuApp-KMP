package com.rgk.qhatu.domain.repository

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.model.Product

interface ProductRepository {
    suspend fun fetchLocal(): SyncResult<List<Product>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<Product>>
    suspend fun uploadRemote() : SyncResult<Boolean>
    suspend fun updateLocal(register: Product): SyncResult<Boolean>
    suspend fun saveLocal(registers: List<Product>) : SyncResult<Boolean>
    suspend fun syncLocalToRemote(): SyncResult<Boolean>
    suspend fun syncRemoteToLocal(): SyncResult<Boolean>
}