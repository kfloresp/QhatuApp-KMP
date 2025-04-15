package com.rgk.qhatu.data.repository

import com.rgk.qhatu.data.database.dao.ProductDao
import com.rgk.qhatu.data.remote.ProductRemoteDataSource
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.mapper.toDomain
import com.rgk.qhatu.domain.mapper.toEntity
import com.rgk.qhatu.domain.mapper.toModel
import com.rgk.qhatu.domain.model.Product
import com.rgk.qhatu.domain.repository.ProductRepository
import com.rgk.qhatu.domain.util.TimeUtils

class ProductRepositoryImpl(
    private val sourceRemote: ProductRemoteDataSource,
    private val sourceLocal: ProductDao
) : ProductRepository {
    override suspend fun fetchLocal(): SyncResult<List<Product>> {
        return try {
            val data = sourceLocal.fetchAll().map {
                it.toDomain()
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

    override suspend fun uploadRemote(): SyncResult<Boolean> {
        return try {
            val data = sourceLocal.fetchAll().map {
                it.toModel()
            }
            data.forEach {
                sourceRemote.uploadCollection(it)
            }
            SyncResult.Success(true)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun updateLocal(register: Product): SyncResult<Boolean> {
        return try {
            sourceLocal.update(register.toEntity())
            SyncResult.Success(true)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun saveLocal(registers: List<Product>): SyncResult<Boolean> {
        return try {
            sourceLocal.save(registers.map { it.toEntity() })
            SyncResult.Success(true)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun syncLocalToRemote(): SyncResult<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun syncRemoteToLocal(): SyncResult<Boolean> {
        return try {
            val localSyncedIds = sourceLocal.getSyncedIds()
            val remoteClients = sourceRemote.fetchCollection()
            sourceLocal.deleteUnsynced()
            val newClients = remoteClients.filterNot { it.id in localSyncedIds }
            sourceLocal.save(newClients.map {
                it.toEntity().copy(fecha_sincronizado = TimeUtils.getCurrentTimestamp())
            })
            SyncResult.Success(true)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

}