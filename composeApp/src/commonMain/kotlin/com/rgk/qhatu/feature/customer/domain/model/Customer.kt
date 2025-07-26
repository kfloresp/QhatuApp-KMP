package com.rgk.qhatu.feature.customer.domain.model

data class Customer(
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

    val isSupplier: Int = 0,

    val isActive: Int = 0,

    val isSynced: Boolean = false,

    val isDeleted: Boolean = false,

    val lastUpdated: Long = 0L,
)