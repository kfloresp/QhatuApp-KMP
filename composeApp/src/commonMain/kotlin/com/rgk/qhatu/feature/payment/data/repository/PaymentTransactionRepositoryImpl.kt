package com.rgk.qhatu.feature.payment.data.repository

import com.rgk.qhatu.feature.payment.data.database.dao.PaymentTransactionDao
import com.rgk.qhatu.feature.payment.data.remote.PaymentTransactionRemoteDataSource
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.payment.domain.mapper.toDomain
import com.rgk.qhatu.feature.payment.domain.mapper.toEntity
import com.rgk.qhatu.feature.payment.domain.mapper.toModel
import com.rgk.qhatu.feature.payment.domain.model.PaymentTransaction
import com.rgk.qhatu.feature.payment.domain.repository.PaymentTransactionRepository
import com.rgk.qhatu.utils.TimeUtils

class PaymentTransactionRepositoryImpl(
    private val sourceRemote: PaymentTransactionRemoteDataSource,
    private val sourceLocal: PaymentTransactionDao
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

    override suspend fun updateLocal(register: PaymentTransaction): SyncResult<Boolean> {
        return try {
            sourceLocal.update(register.toEntity())
            SyncResult.Success(true)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun saveLocal(registers: List<PaymentTransaction>): SyncResult<Boolean> {
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