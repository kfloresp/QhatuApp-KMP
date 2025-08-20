package com.rgk.qhatu.feature.product.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.product.domain.model.ImageProduct
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.shared.SharedImage

interface ProductRepository {
    suspend fun fetchLocal(productId: String?): SyncResult<List<Product>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<Product>>
    suspend fun upsertLocal(register: Product): SyncResult<Unit>
    suspend fun upsertImageProduct(register: List<ImageProduct>): SyncResult<Unit>
    suspend fun fetchImageProduct(productId: String): SyncResult<List<ImageProduct>>
    suspend fun saveLocal(registers: List<Product>): SyncResult<Unit>
    suspend fun syncLocalToRemote(): SyncResult<Unit>
    suspend fun syncRemoteToLocal(): SyncResult<Unit>
    suspend fun saveImageProductLocal(image: SharedImage): SyncResult<String>
}