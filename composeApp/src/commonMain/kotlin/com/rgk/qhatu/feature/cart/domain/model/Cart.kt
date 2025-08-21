package com.rgk.qhatu.feature.cart.domain.model

import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.utils.TimeUtils

data class Cart(
    val id: String = generateUUID(),
    val createdAt: Long = TimeUtils.getCurrentTimestamp(),
    val isActive: Boolean = true
)