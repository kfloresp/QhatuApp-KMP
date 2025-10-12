package com.rgk.qhatu.feature.setting.domain.repository

import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.setting.domain.model.Category

interface CategoryRepository {
    suspend fun fetchLocal(): List<Category>
    suspend fun fetchRemote(): List<Category>
    suspend fun upsertLocal(register: Category, typeUpsert: TypeUpsert)
    suspend fun saveAllLocal(registers: List<Category>)

}