package com.rgk.qhatu.feature.purchase.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.rgk.qhatu.feature.operation.data.database.entity.OperationEntity

data class PurchaseWithOperationRelation(
    @Embedded val purchase: PurchaseEntity,
    @Relation(
        parentColumn = "operationId",
        entityColumn = "operationId"
    )
    val operation: OperationEntity,
)
