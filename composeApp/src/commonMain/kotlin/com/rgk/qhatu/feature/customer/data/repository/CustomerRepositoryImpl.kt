package com.rgk.qhatu.feature.customer.data.repository

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.feature.customer.data.database.dao.CustomerDao
import com.rgk.qhatu.feature.customer.data.remote.ClientRemoteDataSource
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.feature.customer.domain.mapper.toDomain
import com.rgk.qhatu.feature.customer.domain.mapper.toEntity
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.model.CustomerSummary
import com.rgk.qhatu.feature.customer.domain.repository.CustomerRepository
import com.rgk.qhatu.utils.TimeUtils
import kotlinx.coroutines.delay

class CustomerRepositoryImpl(
    private val sourceRemote: ClientRemoteDataSource,
    private val sourceLocal: CustomerDao,
) : CustomerRepository {

    override suspend fun fetchLocal(
        idCustomer: String?,
        query: String?,
    ): SyncResult<List<Customer>> {
        return try {
            var data: List<Customer> = emptyList()
            if (idCustomer != null || query != null) {
                idCustomer?.let {
                    data = sourceLocal.fetchCustomer(idCustomer).map {
                        it.toDomain()
                    }
                }
                query?.let {
                    data = sourceLocal.fetchCustomerFromQuery(query).map {
                        it.toDomain()
                    }
                }
            }else{
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

    override suspend fun fetchRemote(): SyncResult<List<Customer>> {
        return try {
            val data = sourceRemote.fetchCollection().map {
                it.toDomain()
            }
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun fetchSummary(idCustomer: String): SyncResult<List<CustomerSummary>> {
        return try {
            val data = listOf(
                CustomerSummary("1", "1", "Venta", "5/07/2025", "S/150.0"),
                CustomerSummary("2", "1", "Pago", "5/07/2025", "S/100.0"),
                CustomerSummary("3", "1", "Venta", "5/07/2025", "S/60.0")
            )
            delay(1000L)
            SyncResult.Success(data)
        } catch (e: Exception) {
            SyncResult.Error(e)
        }
    }

    override suspend fun upsertLocal(register: Customer): SyncResult<Unit> {
        return safeCall {
            val isNew = register.id.isEmpty()
            if (isNew) {
                sourceLocal.save(register.toEntity().copy(id = generateUUID()))
            } else {
                sourceLocal.update(register.toEntity())
            }
        }
    }

    override suspend fun saveLocal(registers: List<Customer>): SyncResult<Unit> {
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
                it.toEntity().copy(
                    lastUpdated = TimeUtils.getCurrentTimestamp(),
                    isSynced = true
                )
            })
        }
    }
}