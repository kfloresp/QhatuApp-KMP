package com.rgk.qhatu.domain.feature.product.repository

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.feature.product.model.Product

interface ProductRepository {
    suspend fun fetchLocal(): SyncResult<List<Product>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<Product>>
    suspend fun uploadRemote() : SyncResult<Boolean>
    suspend fun updateLocal(register: Product): SyncResult<Boolean>
    suspend fun saveLocal(registers: List<Product>) : SyncResult<Boolean>
    suspend fun syncLocalToRemote(): SyncResult<Boolean>
    suspend fun syncRemoteToLocal(): SyncResult<Boolean>
    suspend fun getProductFromQuery(query: String, searchType: Int): SyncResult<List<Product>>
}