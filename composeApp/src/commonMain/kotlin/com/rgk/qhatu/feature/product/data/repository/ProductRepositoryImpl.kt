package com.rgk.qhatu.feature.product.data.repository

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.feature.product.data.database.dao.ProductDao
import com.rgk.qhatu.feature.product.data.remote.ProductRemoteDataSource
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.product.domain.mapper.toDomain
import com.rgk.qhatu.feature.product.domain.mapper.toEntity
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.domain.repository.ProductRepository
import com.rgk.qhatu.utils.TimeUtils

class ProductRepositoryImpl(
    private val sourceRemote: ProductRemoteDataSource,
    private val sourceLocal: ProductDao,
) : ProductRepository {
    override suspend fun fetchLocal(
        productId: String?,
    ): SyncResult<List<Product>> {
        return try {
            var data: List<Product> = emptyList()
            productId?.let {
                data = sourceLocal.getProductsWithDetailsById(productId)
            } ?: run {
                data = sourceLocal.getProductsWithDetails()
            }
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun getStats(): SyncResult<SyncStats> {
        return try {
            val data = sourceLocal.getStats()
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun fetchRemote(): SyncResult<List<Product>> {
        return try {
            val data = sourceRemote.fetchCollection().map {
                it.toDomain()
            }
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun updateLocal(register: Product): SyncResult<Unit> {
        return safeCall {
            sourceLocal.update(register.toEntity())
        }
    }

    override suspend fun saveLocal(registers: List<Product>): SyncResult<Unit> {
        return safeCall {
            sourceLocal.save(registers.map { it.toEntity() })
        }
    }

    override suspend fun syncLocalToRemote(): SyncResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun syncRemoteToLocal(): SyncResult<Unit> {
        return safeCall {
            val localSyncedIds = sourceLocal.getSyncedIds()
            val remoteClients = sourceRemote.fetchCollection()
            sourceLocal.deleteUnsynced()
            val newClients = remoteClients.filterNot { it.id in localSyncedIds }
            sourceLocal.save(newClients.map {
                it.toEntity().copy(syncedDate = TimeUtils.getCurrentTimestamp())
            })
        }
    }

}