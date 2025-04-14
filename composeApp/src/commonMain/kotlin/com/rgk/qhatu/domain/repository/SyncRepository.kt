package com.rgk.qhatu.domain.repository
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass

interface SyncRepository {
    fun <T : Any> fetchCollection(collection: String, clazz: KClass<T>): Flow<List<T>>
}