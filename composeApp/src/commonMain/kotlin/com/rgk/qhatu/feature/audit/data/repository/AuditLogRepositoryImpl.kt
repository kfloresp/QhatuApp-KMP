package com.rgk.qhatu.feature.audit.data.repository

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.feature.audit.data.database.dao.AuditLogDao
import com.rgk.qhatu.feature.audit.data.remote.AuditLogRemoteDataSource
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.common.util.getCurrentTimestamp
import com.rgk.qhatu.feature.audit.domain.mapper.toDomain
import com.rgk.qhatu.feature.audit.domain.mapper.toEntity
import com.rgk.qhatu.feature.audit.domain.model.AuditLog
import com.rgk.qhatu.feature.audit.domain.repository.AuditLogRepository

class AuditLogRepositoryImpl(
    private val sourceRemote: AuditLogRemoteDataSource,
    private val sourceLocal: AuditLogDao
) : AuditLogRepository {
    override suspend fun fetchLocal(): SyncResult<List<AuditLog>> {
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

    override suspend fun fetchRemote(): SyncResult<List<AuditLog>> {
        return try {
            val data = sourceRemote.fetchCollection().map {
                it.toDomain()
            }
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun updateLocal(register: AuditLog): SyncResult<Unit> {
        return safeCall {
            sourceLocal.update(register.toEntity())
        }
    }

    override suspend fun saveLocal(registers: List<AuditLog>): SyncResult<Unit> {
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
                it.toEntity().copy(fecha_sincronizado = getCurrentTimestamp())
            })
        }
    }

}