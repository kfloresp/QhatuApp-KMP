package com.rgk.qhatu.feature.purchase.domain.mapper

import com.rgk.qhatu.feature.operation.domain.mapper.toDomain
import com.rgk.qhatu.feature.operation.domain.mapper.toEntity
import com.rgk.qhatu.feature.purchase.data.database.entity.PurchaseWithOperationRelation
import com.rgk.qhatu.feature.purchase.domain.model.PurchaseWithOperation

fun PurchaseWithOperationRelation.toDomain(): PurchaseWithOperation =
    PurchaseWithOperation(
        purchase = purchase.toDomain(),
        operation = operation.toDomain()
    )

fun PurchaseWithOperation.toEntity(): PurchaseWithOperationRelation =
    PurchaseWithOperationRelation(
        purchase = purchase.toEntity(),
        operation = operation.toEntity()
    )