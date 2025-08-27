package com.rgk.qhatu.feature.setting.data.repository

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.feature.setting.data.database.dao.UnitMeasureDao
import com.rgk.qhatu.feature.setting.data.remote.UnitMeasureRemoteDataSource
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.common.util.getCurrentTimestamp
import com.rgk.qhatu.feature.setting.domain.mapper.toDomain
import com.rgk.qhatu.feature.setting.domain.mapper.toEntity
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import com.rgk.qhatu.feature.setting.domain.repository.UnitMeasureRepository

class UnitMeasureRepositoryImpl(
    private val sourceRemote: UnitMeasureRemoteDataSource,
    private val sourceLocal: UnitMeasureDao,
) : UnitMeasureRepository {
    override suspend fun fetchLocal(): SyncResult<List<UnitMeasure>> {
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

    override suspend fun fetchRemote(): SyncResult<List<UnitMeasure>> {
        return try {
            val data = sourceRemote.fetchCollection().map {
                it.toDomain()
            }
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun upsertLocal(register: UnitMeasure): SyncResult<Unit> {
        return safeCall {
            val isNew = register.id.isEmpty()
            if (isNew) {
                sourceLocal.save(register.toEntity().copy(id = generateUUID()))
            } else {
                sourceLocal.update(register.toEntity())
            }
        }
    }

    override suspend fun saveLocal(registers: List<UnitMeasure>): SyncResult<Unit> {
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
                it.toEntity().copy(lastUpdated = getCurrentTimestamp())
            })
        }
    }

}