package com.rgk.qhatu.feature.cart.domain.model

import com.rgk.qhatu.common.util.formatAmount
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.common.util.getCurrentTimestamp
import kotlin.String

data class CartItem(
    val id: String = generateUUID(),
    val cartId: String = "default_cart",
    val productId: String,
    val quantity: Int = 0,
    val unitPrice: Double = 0.0,
    val totalDiscount: Double = 0.0,
    val totalPrice: Double = quantity * unitPrice,
    val batch: String? = null,
    val expirationDate: String? = null,
    val lastUpdated: Long = getCurrentTimestamp(),
){
    val totalPriceAmount: String
        get() = totalPrice.formatAmount()

    val totalDiscountAmount: String
        get() = totalDiscount.formatAmount()

    val unitPriceAmount: String
        get() = unitPrice.formatAmount()
}