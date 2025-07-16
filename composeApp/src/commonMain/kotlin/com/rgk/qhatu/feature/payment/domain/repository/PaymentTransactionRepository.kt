package com.rgk.qhatu.feature.payment.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.payment.domain.model.PaymentTransaction

interface PaymentTransactionRepository {
    suspend fun fetchLocal(): SyncResult<List<PaymentTransaction>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<PaymentTransaction>>
    suspend fun uploadRemote() : SyncResult<Boolean>
    suspend fun updateLocal(register: PaymentTransaction): SyncResult<Boolean>
    suspend fun saveLocal(registers: List<PaymentTransaction>) : SyncResult<Boolean>
    suspend fun syncLocalToRemote(): SyncResult<Boolean>
    suspend fun syncRemoteToLocal(): SyncResult<Boolean>

}