package com.rgk.qhatu.feature.customer.domain.model

data class CustomerFlow(
    val customerFlowId: String,
    val customerId: String,
    val operationId: String,
    val typeFlow: String,
    val dateRegister: Long = 0L,
    val amountRegister: Double,
    val isActive: Boolean = false,
    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,
    val lastUpdated: Long = 0L,
)