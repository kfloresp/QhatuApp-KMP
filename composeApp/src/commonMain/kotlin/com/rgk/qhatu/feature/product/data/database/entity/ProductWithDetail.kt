package com.rgk.qhatu.feature.product.data.database.entity

data class ProductWithDetail(
    val id: String = "",
    val ean: String = "",
    val name: String = "",
    val categoryId: String? = null,
    val category: String = "",
    val storageTypeId: String? = null,
    val storageType: String = "",
    val brandId: String? = null,
    val brand: String = "",
    val unitPrice: String = "",
    val unitMeasureId: String = "",
    val unitMeasure: String = "",
    val isBatch: Boolean = false,
    val isActive: Boolean = false,
    val syncedDate: Long = 0,
    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,
    val lastUpdated: Long = 0,
)