package com.rgk.qhatu.feature.sale.domain.model

import com.rgk.qhatu.feature.operation.domain.model.Operation

data class SaleWithOperation(
    val sale: Sale,
    val operation: Operation,
)