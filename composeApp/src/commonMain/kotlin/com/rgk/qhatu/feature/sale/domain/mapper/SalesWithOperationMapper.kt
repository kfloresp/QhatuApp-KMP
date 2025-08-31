package com.rgk.qhatu.feature.sale.domain.mapper

import com.rgk.qhatu.feature.operation.domain.mapper.toDomain
import com.rgk.qhatu.feature.operation.domain.mapper.toEntity
import com.rgk.qhatu.feature.sale.data.database.entity.SaleWithOperationRelation
import com.rgk.qhatu.feature.sale.domain.model.SaleWithOperation

fun SaleWithOperationRelation.toDomain(): SaleWithOperation {
    return SaleWithOperation(
        sale = this.sale.toDomain(),
        operation = this.operation.toDomain()
    )
}

fun SaleWithOperation.toEntity(): SaleWithOperationRelation {
    return SaleWithOperationRelation(
        sale = this.sale.toEntity(),
        operation = this.operation.toEntity()
    )
}