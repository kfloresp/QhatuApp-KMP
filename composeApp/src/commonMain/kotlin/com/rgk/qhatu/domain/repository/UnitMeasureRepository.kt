package com.rgk.qhatu.domain.repository

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.model.UnitMeasure

interface UnitMeasureRepository {
    suspend fun fetchLocal(): SyncResult<List<UnitMeasure>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<UnitMeasure>>
    suspend fun uploadRemote() : SyncResult<Boolean>
    suspend fun updateLocal(register: UnitMeasure): SyncResult<Boolean>
    suspend fun saveLocal(registers: List<UnitMeasure>) : SyncResult<Boolean>
    suspend fun syncLocalToRemote(): SyncResult<Boolean>
    suspend fun syncRemoteToLocal(): SyncResult<Boolean>

}