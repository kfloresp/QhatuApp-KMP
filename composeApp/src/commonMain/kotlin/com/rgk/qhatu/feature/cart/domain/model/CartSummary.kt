package com.rgk.qhatu.feature.cart.domain.model

import com.rgk.qhatu.common.util.formatAmount

data class CartSummary (
    val total : Double = 0.0,
    val itemCount : Int = 0
){
    val totalSummary: String
        get() = total.formatAmount()
}