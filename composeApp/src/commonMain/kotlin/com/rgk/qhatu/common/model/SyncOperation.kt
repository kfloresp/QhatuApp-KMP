package com.rgk.qhatu.common.model

sealed class SyncOperation<T> {
    data class SaveLocal<T>(val registers: List<T>) : SyncOperation<T>()
    data class UpsertLocal<T>(val register: T) : SyncOperation<T>()
    class LocalToRemote<T> : SyncOperation<T>()
    class RemoteToLocal<T> : SyncOperation<T>()
}

sealed class ImageOperation<T> {
    data class SaveLocal<T>(val registers: List<T>) : ImageOperation<T>()
    data class DeleteLocal<T>(val registers: List<T>) : ImageOperation<T>()
}