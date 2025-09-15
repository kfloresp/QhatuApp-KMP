package com.rgk.qhatu.feature.customer.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class CustomerEntity(
    @PrimaryKey
    val customerId: String,
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