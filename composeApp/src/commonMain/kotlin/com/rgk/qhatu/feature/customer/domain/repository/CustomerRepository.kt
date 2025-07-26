package com.rgk.qhatu.feature.customer.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.customer.domain.model.Customer

interface CustomerRepository {
    suspend fun fetchLocal(): SyncResult<List<Customer>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<Customer>>
    suspend fun upsertLocal(register: Customer): SyncResult<Unit>
    suspend fun saveLocal(registers: List<Customer>) : SyncResult<Unit>
    suspend fun syncLocalToRemote(): SyncResult<Unit>
    suspend fun syncRemoteToLocal(): SyncResult<Unit>
}