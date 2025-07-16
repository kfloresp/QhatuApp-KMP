package com.rgk.qhatu.domain.feature.payment.repository

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.feature.payment.model.ClientPayment

interface ClientPaymentRepository {
    suspend fun fetchLocal(): SyncResult<List<ClientPayment>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<ClientPayment>>
    suspend fun uploadRemote() : SyncResult<Boolean>
    suspend fun updateLocal(register: ClientPayment): SyncResult<Boolean>
    suspend fun saveLocal(registers: List<ClientPayment>) : SyncResult<Boolean>
    suspend fun syncLocalToRemote(): SyncResult<Boolean>
    suspend fun syncRemoteToLocal(): SyncResult<Boolean>

}