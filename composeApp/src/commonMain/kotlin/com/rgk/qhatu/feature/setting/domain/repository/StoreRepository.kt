package com.rgk.qhatu.feature.setting.domain.repository

import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.setting.domain.model.Store

interface StoreRepository {
    suspend fun fetchLocal(): Store?
    suspend fun fetchRemote(): Store?
    suspend fun upsertLocal(register: Store, type: TypeUpsert)
}