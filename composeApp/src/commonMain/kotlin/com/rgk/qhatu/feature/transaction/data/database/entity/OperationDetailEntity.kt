package com.rgk.qhatu.feature.transaction.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operation_detail")
data class OperationDetailEntity(
    @PrimaryKey
    val detailId: String,
    val operationId: Long,
    val productId: Long,
    val quantity: Double,
    val unitPrice: Double,
    val totalPrice: Double = quantity * unitPrice,
    val batch: String? = null,
    val expirationDate: String? = null,
    val lastUpdated: Long = 0L,
    val isSynced: Boolean = false,
    val syncedDate: Long = 0L,
    val isDeleted: Boolean = false,
)
