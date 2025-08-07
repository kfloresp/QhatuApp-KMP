package com.rgk.qhatu.feature.payment.domain.model

data class ClientPayment(
    val id: String,
    val clientId: String,
    val paymentDate: Long,
    val amountPaid: Double,
    val paymentMethodId: String,
    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,
    val lastUpdated: Long = 0L,
)