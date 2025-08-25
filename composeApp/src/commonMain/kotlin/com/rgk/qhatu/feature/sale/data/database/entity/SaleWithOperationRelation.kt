package com.rgk.qhatu.feature.sale.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.rgk.qhatu.feature.operation.data.database.entity.OperationEntity

data class SaleWithOperationRelation(
    @Embedded val sale: SaleEntity,
    @Relation(
        parentColumn = "operationId",
        entityColumn = "operationId"
    )
    val operation: OperationEntity,
)