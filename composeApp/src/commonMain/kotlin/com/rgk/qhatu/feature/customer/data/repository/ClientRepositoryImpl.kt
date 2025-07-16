package com.rgk.qhatu.feature.customer.data.repository

import com.rgk.qhatu.feature.customer.data.database.dao.ClientDao
import com.rgk.qhatu.feature.customer.data.remote.ClientRemoteDataSource
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.customer.domain.mapper.toDomain
import com.rgk.qhatu.feature.customer.domain.mapper.toEntity
import com.rgk.qhatu.feature.customer.domain.mapper.toModel
import com.rgk.qhatu.feature.customer.domain.model.Client
import com.rgk.qhatu.feature.customer.domain.repository.ClientRepository
import com.rgk.qhatu.utils.TimeUtils

class ClientRepositoryImpl(
    private val sourceRemote: ClientRemoteDataSource,
    private val sourceLocal: ClientDao
) : ClientRepository {
    override suspend fun fetchLocal(): SyncResult<List<Client>> {
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

    override suspend fun fetchRemote(): SyncResult<List<Client>> {
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

    override suspend fun updateLocal(register: Client): SyncResult<Boolean> {
        return try {
            sourceLocal.update(register.toEntity())
            SyncResult.Success(true)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun saveLocal(registers: List<Client>): SyncResult<Boolean> {
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
                it.toEntity().copy(
                    fechaSincronizado = TimeUtils.getCurrentTimestamp(),
                    flagSincronizado = 1
                )
            })
            SyncResult.Success(true)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun fetchClient(query: String): SyncResult<List<Client>> {
        return try {
                val data = sourceLocal.fetchClient(query).map {
                    it.toDomain()
                }
                return SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun fetchProvider(query: String): SyncResult<List<Client>> {
        return try {
                val data = sourceLocal.fetchProvider(query).map {
                    it.toDomain()
                }
                return SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }
}