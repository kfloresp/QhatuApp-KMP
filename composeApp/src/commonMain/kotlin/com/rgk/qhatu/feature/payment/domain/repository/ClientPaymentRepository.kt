package com.rgk.qhatu.feature.payment.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.payment.domain.model.ClientPayment

interface ClientPaymentRepository {
    suspend fun fetchLocal(): SyncResult<List<ClientPayment>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<ClientPayment>>
    suspend fun updateLocal(register: ClientPayment): SyncResult<Unit>
    suspend fun saveLocal(registers: List<ClientPayment>) : SyncResult<Unit>
    suspend fun syncLocalToRemote(): SyncResult<Unit>
    suspend fun syncRemoteToLocal(): SyncResult<Unit>

}