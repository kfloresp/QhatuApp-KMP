package com.rgk.qhatu.feature.image_store.domain.model

data class ImageStore(
    val id: String = "",
    val entityId: String,
    val tableStore: TableStore,
    val filename: String = "",
    val isTemp: Boolean = false,
    val isLoading: Boolean = false,
    val syncedDate: Long = 0L,
    val isSynced: Boolean = false,
    val lastUpdated: Long = 0L,
)

enum class TableStore() {
    PRODUCT,
    STORE,
}