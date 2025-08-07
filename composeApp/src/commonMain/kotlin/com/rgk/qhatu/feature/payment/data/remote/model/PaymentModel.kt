package com.rgk.qhatu.feature.payment.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PaymentModel(
    val id: String,
    val clientId: String,
    val paymentDate: Long,
    val amountPaid: Double,
    val paymentMethodId: String,
    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,
    val lastUpdated: Long = 0L,
)