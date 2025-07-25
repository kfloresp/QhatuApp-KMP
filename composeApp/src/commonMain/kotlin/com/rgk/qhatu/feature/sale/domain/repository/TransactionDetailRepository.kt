package com.rgk.qhatu.feature.sale.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.sale.domain.model.TransactionDetail

interface TransactionDetailRepository {
    suspend fun fetchLocal(): SyncResult<List<TransactionDetail>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<TransactionDetail>>
    suspend fun updateLocal(register: TransactionDetail): SyncResult<Unit>
    suspend fun saveLocal(registers: List<TransactionDetail>) : SyncResult<Unit>
    suspend fun syncLocalToRemote(): SyncResult<Unit>
    suspend fun syncRemoteToLocal(): SyncResult<Unit>
}