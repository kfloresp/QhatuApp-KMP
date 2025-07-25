package com.rgk.qhatu.common.model

sealed class SyncResult<out T> {
    data class Success<T>(val data: T) : SyncResult<T>()
    data class Error(val exception: Throwable) : SyncResult<Nothing>()
}