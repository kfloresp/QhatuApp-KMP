package com.rgk.qhatu.domain.common

sealed class SyncOperation<T> {
    data class Save<T>(val registers: List<T>) : SyncOperation<T>()
    data class Update<T>(val register: T) : SyncOperation<T>()
    class Upload<T> : SyncOperation<T>()
    class Download<T> : SyncOperation<T>()
}