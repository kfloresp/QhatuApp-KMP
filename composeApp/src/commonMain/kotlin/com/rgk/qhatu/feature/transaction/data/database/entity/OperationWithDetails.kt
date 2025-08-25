package com.rgk.qhatu.feature.transaction.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.rgk.qhatu.feature.purchase.data.database.entity.PurchaseEntity
import com.rgk.qhatu.feature.sale.data.database.entity.SaleEntity

data class OperationWithDetails(
    @Embedded val operation: OperationEntity,
    @Relation(
        parentColumn = "operationId",
        entityColumn = "operationId"
    )
    val details: List<OperationDetailEntity>
)

data class SaleWithOperation(
    @Embedded val sale: SaleEntity,
    @Relation(
        parentColumn = "operationId",
        entityColumn = "operationId"
    )
    val operation: OperationEntity,
    @Relation(
        parentColumn = "operationId",
        entityColumn = "operationId"
    )
    val details: List<OperationDetailEntity>
)

data class PurchaseWithOperation(
    @Embedded val purchase: PurchaseEntity,
    @Relation(
        parentColumn = "operationId",
        entityColumn = "operationId"
    )
    val operation: OperationEntity,
    @Relation(
        parentColumn = "operationId",
        entityColumn = "operationId"
    )
    val details: List<OperationDetailEntity>
)
