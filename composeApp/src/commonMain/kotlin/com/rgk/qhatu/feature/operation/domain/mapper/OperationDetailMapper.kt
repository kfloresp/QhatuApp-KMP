package com.rgk.qhatu.feature.operation.domain.mapper

import com.rgk.qhatu.feature.operation.data.database.entity.OperationDetailEntity
import com.rgk.qhatu.feature.operation.domain.model.OperationDetail

fun OperationDetailEntity.toDomain(): OperationDetail {
    return OperationDetail(
        detailId = detailId,
        operationId = operationId,
        productId = productId,
        quantity = quantity,
        unitPrice = unitPrice,
        totalPrice = totalPrice,
        batch = batch,
        expirationDate = expirationDate,
        lastUpdated = lastUpdated,
        isSynced = isSynced,
        syncedDate = syncedDate,
        isDeleted = isDeleted
    )
}

fun OperationDetail.toEntity(): OperationDetailEntity {
    return OperationDetailEntity(
        detailId = detailId,
        operationId = operationId,
        productId = productId,
        quantity = quantity,
        unitPrice = unitPrice,
        totalPrice = totalPrice,
        batch = batch,
        expirationDate = expirationDate,
        lastUpdated = lastUpdated,
        isSynced = isSynced,
        syncedDate = syncedDate,
        isDeleted = isDeleted
    )
}
