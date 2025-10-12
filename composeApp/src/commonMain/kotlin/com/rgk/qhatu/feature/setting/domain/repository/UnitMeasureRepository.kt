package com.rgk.qhatu.feature.setting.domain.repository

import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure

interface UnitMeasureRepository {
    suspend fun fetchLocal(): List<UnitMeasure>
    suspend fun fetchRemote(): List<UnitMeasure>
    suspend fun upsertLocal(register: UnitMeasure, typeUpsert: TypeUpsert)
    suspend fun saveAllLocal(registers: List<UnitMeasure>)
}