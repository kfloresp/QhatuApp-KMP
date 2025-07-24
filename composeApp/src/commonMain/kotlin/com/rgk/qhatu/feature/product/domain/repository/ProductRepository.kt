package com.rgk.qhatu.feature.product.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.product.domain.model.Product

interface ProductRepository {
    suspend fun fetchLocal(): SyncResult<List<Product>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<Product>>
    suspend fun updateLocal(register: Product): SyncResult<Unit>
    suspend fun saveLocal(registers: List<Product>) : SyncResult<Unit>
    suspend fun syncLocalToRemote(): SyncResult<Unit>
    suspend fun syncRemoteToLocal(): SyncResult<Unit>
    suspend fun getProductFromQuery(query: String, searchType: Int): SyncResult<List<Product>>
}