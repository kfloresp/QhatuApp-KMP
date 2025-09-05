package com.rgk.qhatu.feature.setting.data.repository

import com.rgk.qhatu.feature.setting.data.database.dao.BrandDao
import com.rgk.qhatu.feature.setting.data.remote.BrandRemoteDataSource
import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.setting.domain.mapper.toDomain
import com.rgk.qhatu.feature.setting.domain.mapper.toEntity
import com.rgk.qhatu.feature.setting.domain.model.Brand
import com.rgk.qhatu.feature.setting.domain.repository.BrandRepository

class BrandRepositoryImpl(
    private val sourceRemote: BrandRemoteDataSource,
    private val sourceLocal: BrandDao,
) : BrandRepository {
    override suspend fun fetchLocal(): List<Brand> {
        val data = sourceLocal.fetchAll().map {
            it.toDomain()
        }
        return data
    }

    override suspend fun fetchRemote(): List<Brand> {
        val data = sourceRemote.fetchCollection().map {
            it.toDomain()
        }
        return data
    }

    override suspend fun upsertLocal(register: Brand, typeUpsert: TypeUpsert) {
        when (typeUpsert) {
            TypeUpsert.NEW -> {
                sourceLocal.save(register.toEntity())
            }

            TypeUpsert.UPDATE -> {
                sourceLocal.update(register.toEntity())

            }
        }
    }

    override suspend fun saveAllLocal(registers: List<Brand>) {
        sourceLocal.save(registers.map { it.toEntity() })
    }
}