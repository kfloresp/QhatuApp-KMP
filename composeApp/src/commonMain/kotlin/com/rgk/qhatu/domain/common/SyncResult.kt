package com.rgk.qhatu.domain.common

sealed class SyncResult<out T> {
    data class Success<T>(val data: T) : SyncResult<T>()
    data class Error(val exception: Throwable) : SyncResult<Nothing>()
}