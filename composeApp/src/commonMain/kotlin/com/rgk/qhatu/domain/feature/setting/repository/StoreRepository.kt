package com.rgk.qhatu.domain.feature.setting.repository

import com.rgk.qhatu.data.feature.setting.remote.model.StoreModel
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.feature.setting.model.Store

interface StoreRepository {
    suspend fun fetchLocal(): SyncResult<List<Store>>
    suspend fun getStats(): SyncResult<SyncStats>
    suspend fun fetchRemote(): SyncResult<List<StoreModel>>
    suspend fun uploadRemote(store: Store): SyncResult<Boolean>
    suspend fun saveLocal(stores: List<Store>): SyncResult<Boolean>
    suspend fun syncRemoteToLocal(): SyncResult<Boolean>
}