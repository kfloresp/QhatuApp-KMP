package com.rgk.qhatu.feature.operation.domain.model

data class OperationWithDetail(
    val operation: Operation,
    val details: List<OperationDetail>,
)