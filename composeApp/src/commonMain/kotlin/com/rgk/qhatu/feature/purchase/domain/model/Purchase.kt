package com.rgk.qhatu.feature.purchase.domain.model

data class Purchase(
    val operationId: String,
    val supplierId: String,
    val documentRef: String? = null,
    val paymentTerms: String? = null,
)