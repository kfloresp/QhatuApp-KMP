package com.rgk.qhatu.data.repository

import com.rgk.qhatu.data.remote.SyncRemoteDataSource
import com.rgk.qhatu.domain.repository.SyncRepository
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass

class SyncRepositoryImpl(val syncRemoteDataSource: SyncRemoteDataSource): SyncRepository {
    override fun <T : Any> fetchCollection(collection: String, clazz: KClass<T>): Flow<List<T>> {
        return syncRemoteDataSource.fetchCollection(collection, clazz)
    }
}