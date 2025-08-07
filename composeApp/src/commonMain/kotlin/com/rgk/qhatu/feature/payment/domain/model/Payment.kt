package com.rgk.qhatu.feature.payment.domain.model

data class Payment(
    val id: String = "",
    val clientId: String = "",
    val paymentDate: Long = 0L,
    val amountPaid: Double = 0.0,
    val paymentMethodId: String = "",
    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,
    val lastUpdated: Long = 0L,
)