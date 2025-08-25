package com.rgk.qhatu.feature.sale.domain.model

import com.rgk.qhatu.feature.operation.domain.model.Operation
import com.rgk.qhatu.feature.operation.domain.model.OperationDetail
import com.rgk.qhatu.feature.operation.domain.model.OperationType

data class SaleWithOperation(
    val operation: Operation = Operation(type = OperationType.SALE),
    val sale: Sale,
    val details: List<OperationDetail> = emptyList(),
)