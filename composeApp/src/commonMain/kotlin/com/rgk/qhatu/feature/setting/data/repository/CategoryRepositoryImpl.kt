package com.rgk.qhatu.feature.setting.data.repository

import com.rgk.qhatu.feature.setting.data.database.dao.CategoryDao
import com.rgk.qhatu.feature.setting.data.remote.CategoryRemoteDataSource
import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.setting.domain.mapper.toDomain
import com.rgk.qhatu.feature.setting.domain.mapper.toEntity
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val sourceRemote: CategoryRemoteDataSource,
    private val sourceLocal: CategoryDao,
) : CategoryRepository {

    override suspend fun fetchLocal(): List<Category> {
        return sourceLocal.fetchAll().map {
            it.toDomain()
        }
    }

    override suspend fun fetchRemote(): List<Category> {
        return sourceRemote.fetchCollection().map {
            it.toDomain()
        }
    }

    override suspend fun upsertLocal(register: Category, typeUpsert: TypeUpsert) {
        when (typeUpsert) {
            TypeUpsert.NEW -> {
                sourceLocal.save(register.toEntity())
            }

            TypeUpsert.UPDATE -> {
                sourceLocal.update(register.toEntity())
            }
        }
    }

    override suspend fun saveAllLocal(registers: List<Category>) {
        sourceLocal.save(registers.map { it.toEntity() })
    }

}