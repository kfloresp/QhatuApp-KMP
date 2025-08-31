package com.rgk.qhatu.feature.cart.domain.model

import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.common.util.getCurrentTimestamp

data class Cart(
    val id: String = generateUUID(),
    val createdAt: Long = getCurrentTimestamp(),
    val isActive: Boolean = true
)