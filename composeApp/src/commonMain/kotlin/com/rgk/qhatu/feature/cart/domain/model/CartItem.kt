package com.rgk.qhatu.feature.cart.domain.model

import com.rgk.qhatu.common.util.formatAmount
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.common.util.TimeUtils
import kotlin.String

data class CartItem(
    val id: String = generateUUID(),
    val cartId: String = "default_cart",
    val productId: String,
    val quantity: Int = 0,
    val unitPrice: Double = 0.0,
    val totalPrice: Double = quantity * unitPrice,
    val lastUpdated: Long = TimeUtils.getCurrentTimestamp(),
){
    val totalPriceAmount: String
        get() = totalPrice.formatAmount()
    val unitPriceAmount: String
        get() = unitPrice.formatAmount()
}