package com.rgk.qhatu.feature.setting.data.repository

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.feature.setting.data.database.dao.UnitMeasureDao
import com.rgk.qhatu.feature.setting.data.remote.UnitMeasureRemoteDataSource
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.common.util.getCurrentTimestamp
import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.setting.domain.mapper.toDomain
import com.rgk.qhatu.feature.setting.domain.mapper.toEntity
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import com.rgk.qhatu.feature.setting.domain.repository.UnitMeasureRepository

class UnitMeasureRepositoryImpl(
    private val sourceRemote: UnitMeasureRemoteDataSource,
    private val sourceLocal: UnitMeasureDao,
) : UnitMeasureRepository {
    override suspend fun fetchLocal(): List<UnitMeasure> {
        return sourceLocal.fetchAll().map {
            it.toDomain()
        }
    }

    override suspend fun fetchRemote(): List<UnitMeasure> {
        return sourceRemote.fetchCollection().map {
            it.toDomain()
        }
    }

    override suspend fun upsertLocal(register: UnitMeasure, typeUpsert: TypeUpsert) {
        when (typeUpsert) {
            TypeUpsert.NEW -> {
                sourceLocal.save(register.toEntity())

            }

            TypeUpsert.UPDATE -> {
                sourceLocal.update(register.toEntity())

            }
        }
    }

    override suspend fun saveAllLocal(registers: List<UnitMeasure>) {
        sourceLocal.save(registers.map { it.toEntity() })
    }
}