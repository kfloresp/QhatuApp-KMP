package com.rgk.qhatu.feature.cart.data.database.entity

data class CartItemWithDetail(
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
    val idCart: String = "",
    val quantityCart: Int = 0,
    val unitPriceCart: Double = 0.0,
    val totalPriceCart: Double = 0.0,
)