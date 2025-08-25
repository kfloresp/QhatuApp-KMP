package com.rgk.qhatu.feature.purchase.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.rgk.qhatu.feature.operation.data.database.entity.OperationEntity

@Entity(
    tableName = "purchase",
    foreignKeys = [
        ForeignKey(
            entity = OperationEntity::class,
            parentColumns = ["operationId"],
            childColumns = ["operationId"],
            onDelete = ForeignKey.Companion.CASCADE
        )
    ],
    indices = [Index("operationId")]
)
data class PurchaseEntity(
    @PrimaryKey
    val operationId: String,
    val supplierId: String,
    val documentRef: String? = null,
    val paymentTerms: String? = null
)