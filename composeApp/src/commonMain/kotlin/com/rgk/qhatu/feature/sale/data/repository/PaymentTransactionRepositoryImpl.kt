package com.rgk.qhatu.feature.sale.data.repository

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.sale.domain.mapper.toDomain
import com.rgk.qhatu.feature.sale.domain.mapper.toEntity
import com.rgk.qhatu.feature.sale.domain.model.PaymentTransaction
import com.rgk.qhatu.feature.sale.domain.repository.PaymentTransactionRepository
import com.rgk.qhatu.feature.sale.data.database.dao.PaymentTransactionDao
import com.rgk.qhatu.feature.sale.data.remote.PaymentTransactionRemoteDataSource
import com.rgk.qhatu.utils.TimeUtils

class PaymentTransactionRepositoryImpl(
    private val sourceRemote: PaymentTransactionRemoteDataSource,
    private val sourceLocal: PaymentTransactionDao,
) : PaymentTransactionRepository {
    override suspend fun fetchLocal(): SyncResult<List<PaymentTransaction>> {
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

    override suspend fun fetchRemote(): SyncResult<List<PaymentTransaction>> {
        return try {
            val data = sourceRemote.fetchCollection().map {
                it.toDomain()
            }
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun updateLocal(register: PaymentTransaction): SyncResult<Unit> {
        return safeCall {
            sourceLocal.update(register.toEntity())
        }
    }

    override suspend fun saveLocal(registers: List<PaymentTransaction>): SyncResult<Unit> {
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
                it.toEntity().copy(fecha_sincronizado = TimeUtils.getCurrentTimestamp())
            })
        }
    }

}