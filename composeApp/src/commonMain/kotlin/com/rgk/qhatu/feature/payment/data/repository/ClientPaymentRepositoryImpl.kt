package com.rgk.qhatu.feature.payment.data.repository

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.feature.payment.data.database.dao.ClientPaymentDao
import com.rgk.qhatu.feature.payment.data.remote.ClientPaymentRemoteDataSource
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.payment.domain.mapper.toDomain
import com.rgk.qhatu.feature.payment.domain.mapper.toEntity
import com.rgk.qhatu.feature.payment.domain.model.ClientPayment
import com.rgk.qhatu.feature.payment.domain.repository.ClientPaymentRepository
import com.rgk.qhatu.utils.TimeUtils

class ClientPaymentRepositoryImpl(
    private val sourceRemote: ClientPaymentRemoteDataSource,
    private val sourceLocal: ClientPaymentDao
) : ClientPaymentRepository {
    override suspend fun fetchLocal(): SyncResult<List<ClientPayment>> {
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
    override suspend fun fetchRemote(): SyncResult<List<ClientPayment>> {
        return try {
            val data = sourceRemote.fetchCollection().map {
                it.toDomain()
            }
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun updateLocal(register: ClientPayment): SyncResult<Unit> {
        return safeCall {
            sourceLocal.update(register.toEntity())
        }
    }

    override suspend fun saveLocal(registers: List<ClientPayment>): SyncResult<Unit> {
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
                it.toEntity().copy(paymentDate = TimeUtils.getCurrentTimestamp())
            })
        }
    }

}