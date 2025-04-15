package com.rgk.qhatu.domain.util

import com.rgk.qhatu.domain.common.SyncResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

inline fun <T, R> wrapToSyncResult(
    crossinline block: suspend () -> Flow<List<T>>,
    crossinline mapper: (T) -> R
): Flow<SyncResult<List<R>>> = flow {
    try {
        block().collect { list ->
            emit(SyncResult.Success(list.map { mapper(it) }))
        }
    } catch (e: Exception) {
        emit(SyncResult.Error(e))
    }
}

inline fun <T, R> wrapToSyncResultObject(
    crossinline block: suspend () -> Flow<T>,
    crossinline mapper: (T) -> R
): Flow<SyncResult<R>> = flow {
    try {
        block().collect { obj ->
            emit(SyncResult.Success(mapper(obj)))
        }
    } catch (e: Exception) {
        emit(SyncResult.Error(e))
    }
}
