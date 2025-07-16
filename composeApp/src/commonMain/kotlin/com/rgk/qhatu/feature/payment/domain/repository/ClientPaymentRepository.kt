package com.rgk.qhatu.feature.payment.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.payment.domain.model.ClientPayment

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