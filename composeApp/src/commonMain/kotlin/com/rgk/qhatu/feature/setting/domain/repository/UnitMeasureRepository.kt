package com.rgk.qhatu.feature.setting.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure

interface UnitMeasureRepository {
    suspend fun fetchLocal(): SyncResult<List<UnitMeasure>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<UnitMeasure>>
    suspend fun upsertLocal(register: UnitMeasure): SyncResult<Unit>
    suspend fun saveLocal(registers: List<UnitMeasure>) : SyncResult<Unit>
    suspend fun syncLocalToRemote(): SyncResult<Unit>
    suspend fun syncRemoteToLocal(): SyncResult<Unit>

}