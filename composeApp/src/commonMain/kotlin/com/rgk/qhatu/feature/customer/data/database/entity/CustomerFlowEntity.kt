package com.rgk.qhatu.feature.customer.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_flow")
data class CustomerFlowEntity(
    @PrimaryKey
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