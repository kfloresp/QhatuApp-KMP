package com.rgk.qhatu.feature.cart.domain.model

import com.rgk.qhatu.common.util.formatAmount

data class CartSummary(
    val subtotalWithIgv: Double = 0.0,
    val subtotalDiscount: Double = 0.0,
    val total: Double = subtotalWithIgv - subtotalDiscount,
    val itemCount: Int = 0,
) {
    val totalSummary: String
        get() = total.formatAmount()

    val subtotalDiscountSummary: String
        get() = subtotalDiscount.formatAmount()

    val subTotalWithIgvSummary: String
        get() = subtotalWithIgv.formatAmount()

    val itemCountSummary: String
        get() = if (itemCount > 0) "(${itemCount})" else ""

    val hasItems: Boolean
        get() = if (itemCount > 0) true else false

    val hasSubtotalWithIgv: Boolean
        get() = if (subtotalWithIgv > 0) true else false

    val hasSubtotalDiscount: Boolean
        get() = if (subtotalDiscount > 0) true else false

    val hasTotal: Boolean
        get() = if (total > 0) true else false
}