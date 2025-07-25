package com.rgk.qhatu.common.extension

import com.rgk.qhatu.common.model.SyncResult

inline fun <T> safeCall(action: () -> T): SyncResult<T> {
    return try {
        SyncResult.Success(action())
    } catch (e: Throwable) {
        SyncResult.Error(e)
    }
}