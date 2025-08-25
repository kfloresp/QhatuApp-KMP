package com.rgk.qhatu.feature.sale.domain.model

data class Sale(
    val operationId: String = "",
    val customerId: String = "",
    val paymentMethodId: String = "",
    val paymentOperationNo: String? = null,
    val amountPaid: Double? = null,
    val changeReturned: Double? = null,
)