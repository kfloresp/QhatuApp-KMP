package com.rgk.qhatu.feature.operation.domain.mapper

import com.rgk.qhatu.feature.operation.data.database.entity.OperationEntity
import com.rgk.qhatu.feature.operation.domain.model.Operation
import com.rgk.qhatu.feature.operation.domain.model.OperationStatus
import com.rgk.qhatu.feature.operation.domain.model.OperationType

fun OperationEntity.toDomain(): Operation {
    return Operation(
        operationId = operationId,
        type = OperationType.entries.first { it.value == type },
        status = OperationStatus.entries.first { it.value == status },
        operationDate = operationDate,
        lastUpdated = lastUpdated,
        isSynced = isSynced,
        syncedDate = syncedDate,
        isDeleted = isDeleted
    )
}

fun Operation.toEntity(): OperationEntity {
    return OperationEntity(
        operationId = operationId,
        type = type.value,
        status = status.value,
        operationDate = operationDate,
        lastUpdated = lastUpdated,
        isSynced = isSynced,
        syncedDate = syncedDate,
        isDeleted = isDeleted
    )
}
