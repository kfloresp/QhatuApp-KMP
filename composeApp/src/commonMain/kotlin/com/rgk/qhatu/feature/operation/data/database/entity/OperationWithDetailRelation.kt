package com.rgk.qhatu.feature.operation.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class OperationWithDetailRelation(
    @Embedded val operation: OperationEntity,
    @Relation(
        parentColumn = "operationId",
        entityColumn = "operationId"
    )
    val details: List<OperationDetailEntity>
)