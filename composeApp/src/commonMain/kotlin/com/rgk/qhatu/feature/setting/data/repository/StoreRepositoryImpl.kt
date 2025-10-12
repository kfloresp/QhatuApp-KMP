package com.rgk.qhatu.feature.setting.data.repository

import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.setting.data.database.dao.StoreDao
import com.rgk.qhatu.feature.setting.data.remote.StoreRemoteDataSource
import com.rgk.qhatu.feature.setting.domain.mapper.toDomain
import com.rgk.qhatu.feature.setting.domain.mapper.toEntity
import com.rgk.qhatu.feature.setting.domain.model.Store
import com.rgk.qhatu.feature.setting.domain.repository.StoreRepository

class StoreRepositoryImpl(
    private val sourceRemote: StoreRemoteDataSource,
    private val sourceLocal: StoreDao,
) : StoreRepository {

    override suspend fun fetchLocal(): Store? {
        return sourceLocal.fetchLocal()?.toDomain()
    }

    override suspend fun fetchRemote(): Store? {
        return sourceRemote.fetchCollection().map {
            it.toDomain()
        }.first()
    }

    override suspend fun upsertLocal(register: Store, type: TypeUpsert) {
        when (type) {
            TypeUpsert.NEW -> {
                sourceLocal.save(register.toEntity())
            }

            TypeUpsert.UPDATE -> {
                sourceLocal.update(register.toEntity())
            }
        }
    }
}