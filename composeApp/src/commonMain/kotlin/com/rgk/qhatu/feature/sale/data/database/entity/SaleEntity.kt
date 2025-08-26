package com.rgk.qhatu.feature.sale.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.rgk.qhatu.feature.operation.data.database.entity.OperationEntity
import com.rgk.qhatu.feature.sale.domain.model.SaleVaucherType

@Entity(
    tableName = "sale",
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
data class SaleEntity(
    @PrimaryKey
    val operationId: String,
    val customerId: String,
    val vaucherType: String,
    val vaucherOperationNo: String,
    val paymentMethodId: String,
    val paymentOperationNo: String? = null,
    val amountPaid: Double? = null,
    val changeReturned: Double? = null,
)