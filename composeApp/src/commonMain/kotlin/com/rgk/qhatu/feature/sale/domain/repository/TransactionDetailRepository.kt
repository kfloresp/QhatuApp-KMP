package com.rgk.qhatu.feature.sale.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.sale.domain.model.TransactionDetail

interface TransactionDetailRepository {
    suspend fun fetchLocal(): SyncResult<List<TransactionDetail>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<TransactionDetail>>
    suspend fun uploadRemote() : SyncResult<Boolean>
    suspend fun updateLocal(register: TransactionDetail): SyncResult<Boolean>
    suspend fun saveLocal(registers: List<TransactionDetail>) : SyncResult<Boolean>
    suspend fun syncLocalToRemote(): SyncResult<Boolean>
    suspend fun syncRemoteToLocal(): SyncResult<Boolean>
}