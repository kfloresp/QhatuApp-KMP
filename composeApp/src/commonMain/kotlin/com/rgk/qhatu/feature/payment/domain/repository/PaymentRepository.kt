package com.rgk.qhatu.feature.payment.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.payment.domain.model.Payment

interface PaymentRepository {
    suspend fun fetchLocal(): SyncResult<List<Payment>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<Payment>>
    suspend fun updateLocal(register: Payment): SyncResult<Unit>
    suspend fun saveLocal(registers: List<Payment>) : SyncResult<Unit>
    suspend fun syncLocalToRemote(): SyncResult<Unit>
    suspend fun syncRemoteToLocal(): SyncResult<Unit>

}