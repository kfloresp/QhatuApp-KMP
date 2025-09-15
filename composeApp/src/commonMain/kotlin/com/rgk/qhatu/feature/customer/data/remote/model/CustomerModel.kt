package com.rgk.qhatu.feature.customer.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CustomerModel(
    val id: String,
    val businessName: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val motherLastName: String? = null,
    val documentType: String,
    val documentNumber: String,
    val phoneNumber: String? = null,
    val address: String? = null,
    val email: String? = null,
    val pendingAmount: Double? = 0.0,
    val pendingAmountMax: Double? = 0.0,
    val isSupplier: Boolean = false,
    val isActive: Boolean = false,
    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,
    val lastUpdated: Long = 0L,
)