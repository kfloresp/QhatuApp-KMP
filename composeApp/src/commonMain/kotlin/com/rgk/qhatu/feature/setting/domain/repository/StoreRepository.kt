package com.rgk.qhatu.feature.setting.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.setting.data.remote.model.StoreModel
import com.rgk.qhatu.feature.setting.domain.model.Store

interface StoreRepository {
    suspend fun fetchLocal(): SyncResult<List<Store>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<StoreModel>>
    suspend fun upsertLocal(register: Store): SyncResult<Unit>
    suspend fun saveLocal(stores: List<Store>): SyncResult<Unit>
    suspend fun syncRemoteToLocal(): SyncResult<Unit>
}