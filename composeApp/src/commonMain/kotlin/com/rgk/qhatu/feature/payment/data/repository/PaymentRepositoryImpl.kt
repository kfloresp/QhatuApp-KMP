package com.rgk.qhatu.feature.payment.data.repository

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.feature.payment.data.database.dao.PaymentDao
import com.rgk.qhatu.feature.payment.data.remote.ClientPaymentRemoteDataSource
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.feature.payment.domain.mapper.toDomain
import com.rgk.qhatu.feature.payment.domain.mapper.toEntity
import com.rgk.qhatu.feature.payment.domain.model.Payment
import com.rgk.qhatu.feature.payment.domain.repository.PaymentRepository
import com.rgk.qhatu.utils.TimeUtils

class PaymentRepositoryImpl(
    private val sourceRemote: ClientPaymentRemoteDataSource,
    private val sourceLocal: PaymentDao,
) : PaymentRepository {
    override suspend fun fetchLocal(idPayment: String?): SyncResult<List<Payment>> {
        return try {
            var data: List<Payment> = emptyList()
            idPayment?.let {
                data = sourceLocal.fetchById(idPayment)
            } ?: run {
                data = sourceLocal.getPaymentsWithDetails()
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

    override suspend fun fetchRemote(): SyncResult<List<Payment>> {
        return try {
            val data = sourceRemote.fetchCollection().map {
                it.toDomain()
            }
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun upsertLocal(register: Payment): SyncResult<Unit> {
        return safeCall {
            val isNew = register.id.isEmpty()
            if (isNew) {
                sourceLocal.save(register.toEntity().copy(id = generateUUID()))
            } else {
                sourceLocal.update(register.toEntity())
            }
        }
    }

    override suspend fun saveLocal(registers: List<Payment>): SyncResult<Unit> {
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