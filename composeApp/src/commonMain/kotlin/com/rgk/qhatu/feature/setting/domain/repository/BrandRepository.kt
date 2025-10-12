package com.rgk.qhatu.feature.setting.domain.repository

import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.setting.domain.model.Brand

interface BrandRepository {
    suspend fun fetchLocal(): List<Brand>
    suspend fun fetchRemote(): List<Brand>
    suspend fun upsertLocal(register: Brand, typeUpsert: TypeUpsert)
    suspend fun saveAllLocal(registers: List<Brand>)
}