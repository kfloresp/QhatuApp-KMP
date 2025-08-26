package com.rgk.qhatu.feature.operation.domain.model

import com.rgk.qhatu.common.util.TimeUtils.getCurrentTimestamp
import com.rgk.qhatu.common.util.generateUUID

data class OperationDetail(
    val detailId: String = generateUUID(),
    val operationId: String = "",
    val productId: String,
    val quantity: Int,
    val unitPrice: Double,
    val totalPrice: Double = quantity * unitPrice,
    val batch: String? = null,
    val expirationDate: String? = null,
    val lastUpdated: Long = getCurrentTimestamp(),
    val isSynced: Boolean = false,
    val syncedDate: Long = 0L,
    val isDeleted: Boolean = false,
)


