package com.rgk.qhatu.feature.setting.data.repository

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.common.util.getCurrentTimestamp
import com.rgk.qhatu.feature.setting.data.database.dao.StoreDao
import com.rgk.qhatu.feature.setting.data.remote.StoreRemoteDataSource
import com.rgk.qhatu.feature.setting.domain.mapper.toDomain
import com.rgk.qhatu.feature.setting.domain.mapper.toEntity
import com.rgk.qhatu.feature.setting.domain.model.Store
import com.rgk.qhatu.feature.setting.domain.repository.StoreRepository

class StoreRepositoryImpl(
    private val sourceRemote: StoreRemoteDataSource,
    private val sourceLocal: StoreDao,
) : StoreRepository {

    override suspend fun fetchLocal(): SyncResult<Store> {
        return try {
            val data = sourceLocal.fetchAll()
            if (data.isEmpty()) {
                SyncResult.Success(Store())
            } else {
                val firstData = data.map {
                    it.toDomain()
                }.first()
                SyncResult.Success(firstData)
            }
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

    override suspend fun fetchRemote(): SyncResult<List<Store>> {
        return safeCall {
            sourceRemote.fetchCollection().map {
                it.toDomain()
            }
        }
    }

    override suspend fun upsertLocal(register: Store): SyncResult<Unit> {
        return safeCall {
            val isNew = register.id.isEmpty()
            if (isNew) {
                sourceLocal.save(register.toEntity().copy(id = generateUUID()))
            } else {
                sourceLocal.update(register.toEntity())
            }
        }
    }

    override suspend fun saveLocal(registers: List<Store>): SyncResult<Unit> {
        return safeCall {
            sourceLocal.save(registers.map { it.toEntity() })
        }
    }

    override suspend fun syncRemoteToLocal(): SyncResult<Unit> {
        return safeCall {
            val localSyncedIds = sourceLocal.getSyncedIds()
            val remoteClients = sourceRemote.fetchCollection()
            sourceLocal.deleteUnsynced()
            val newClients = remoteClients.filterNot { it.id in localSyncedIds }
            sourceLocal.save(newClients.map {
                it.toEntity().copy(lastUpdated = getCurrentTimestamp())
            })
        }
    }


}