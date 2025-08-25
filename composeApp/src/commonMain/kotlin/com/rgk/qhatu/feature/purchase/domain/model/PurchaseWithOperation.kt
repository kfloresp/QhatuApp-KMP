package com.rgk.qhatu.feature.purchase.domain.model

import com.rgk.qhatu.feature.operation.domain.model.Operation

data class PurchaseWithOperation(
    val purchase: Purchase,
    val operation: Operation,
)
