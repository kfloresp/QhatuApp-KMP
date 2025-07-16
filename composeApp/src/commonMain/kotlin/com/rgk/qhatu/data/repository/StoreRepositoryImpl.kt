package com.rgk.qhatu.data.repository

import com.rgk.qhatu.data.database.dao.StoreDao
import com.rgk.qhatu.data.remote.StoreRemoteDataSource
import com.rgk.qhatu.data.remote.model.StoreModel
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.mapper.toDomain
import com.rgk.qhatu.domain.mapper.toEntity
import com.rgk.qhatu.domain.mapper.toModel
import com.rgk.qhatu.domain.model.Store
import com.rgk.qhatu.domain.repository.StoreRepository

class StoreRepositoryImpl(
    private val sourceRemote: StoreRemoteDataSource,
    private val sourceLocal: StoreDao
) : StoreRepository {

    override suspend fun fetchLocal(): SyncResult<List<Store>> {
        return try {
            val data = sourceLocal.fetchAll().map { it.toDomain() }
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

    override suspend fun fetchRemote(): SyncResult<List<StoreModel>> {
        return try {
            val data = sourceRemote.fetchCollection()
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun uploadRemote(store: Store): SyncResult<Boolean> {
        return try {
            val storeModel = store.toEntity().toModel()
            sourceRemote.uploadCollection(storeModel)
            SyncResult.Success(true)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun saveLocal(stores: List<Store>): SyncResult<Boolean> {
        return try {
            sourceLocal.save(stores.map { it.toEntity() })
            SyncResult.Success(true)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun syncRemoteToLocal(): SyncResult<Boolean> {
        return try {
            val localSyncedIds = sourceLocal.getSyncedIds()

            val remoteStores = sourceRemote.fetchCollection()

            sourceLocal.deleteUnsynced()

            val newStores = remoteStores.filterNot { it.id in localSyncedIds }

            if (newStores.isNotEmpty()) {
                sourceLocal.save(newStores.map { it.toEntity() })
            }

            SyncResult.Success(true)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }
}