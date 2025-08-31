package com.rgk.qhatu.feature.operation.domain.mapper

import com.rgk.qhatu.feature.operation.data.database.entity.OperationWithDetailRelation
import com.rgk.qhatu.feature.operation.domain.model.OperationWithDetail

fun OperationWithDetailRelation.toDomain(): OperationWithDetail {
    return OperationWithDetail(
        operation = this.operation.toDomain(),
        details = this.details.map { it.toDomain() }
    )
}

fun OperationWithDetail.toEntity(): OperationWithDetailRelation {
    return OperationWithDetailRelation(
        operation = this.operation.toEntity(),
        details = this.details.map { it.toEntity() }
    )
}