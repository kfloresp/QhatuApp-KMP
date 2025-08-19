package com.rgk.qhatu.feature.product.domain.model

data class ImageProduct(
    val id: String = "",
    val productId: String = "",
    val filename:String = "",
    val isTemp: Boolean = false,
    val syncedDate: Long = 0L,
    val isSynced: Boolean = false,
    val lastUpdated: Long = 0L,
)