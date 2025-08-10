package com.rgk.qhatu.feature.setting.data.repository

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.feature.setting.data.database.dao.ConfigurationDao
import com.rgk.qhatu.feature.setting.data.remote.ConfigurationRemoteDataSource
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.setting.domain.mapper.toDomain
import com.rgk.qhatu.feature.setting.domain.mapper.toEntity
import com.rgk.qhatu.feature.setting.domain.model.Configuration
import com.rgk.qhatu.feature.setting.domain.repository.ConfigurationRepository
import com.rgk.qhatu.utils.TimeUtils

class ConfigurationRepositoryImpl(
    private val sourceRemote: ConfigurationRemoteDataSource,
    private val sourceLocal: ConfigurationDao,
) : ConfigurationRepository {
    override suspend fun fetchLocal(param: String?): SyncResult<List<Configuration>> {
        return try {
            var data: List<Configuration> = listOf()
            param?.let { params ->
                data = sourceLocal.fetchParam(params).map {
                    it.toDomain()
                }
            } ?: run {
                data = sourceLocal.fetchAll().map {
                    it.toDomain()
                }
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

    override suspend fun fetchRemote(): SyncResult<List<Configuration>> {
        return try {
            val data = sourceRemote.fetchCollection().map {
                it.toDomain()
            }
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun saveLocal(registers: List<Configuration>): SyncResult<Unit> {
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
                it.toEntity().copy(lastUpdated = TimeUtils.getCurrentTimestamp())
            })
        }
    }

}