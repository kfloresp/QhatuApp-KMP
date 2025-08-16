package com.rgk.qhatu.feature.product.domain.model

import com.rgk.qhatu.common.util.formatAmount
import com.rgk.qhatu.common.util.orZero

data class Product(
    val id: String = "",
    val ean: String = "",
    val name: String = "",
    val categoryId: String? = null,
    val category:String = "",
    val storageTypeId: String? = null,
    val storageType:String = "",
    val brandId: String? = null,
    val brand:String = "",
    val unitPrice: Double = 0.0,
    val unitMeasureId: String = "",
    val unitMeasure: String = "",
    val isBatch: Boolean = false,
    val isActive: Boolean = false,
    val syncedDate: Long = 0,
    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,
    val lastUpdated: Long = 0,
){
    val unitPriceValue: String
        get() = unitPrice.formatAmount(CURRENCY_SYMBOL)
}
private val CURRENCY_SYMBOL = "S/."