package com.rgk.qhatu.feature.sale.domain.mapper

import com.rgk.qhatu.feature.sale.data.database.entity.SaleEntity
import com.rgk.qhatu.feature.sale.domain.model.Sale

fun SaleEntity.toDomain(): Sale {
    return Sale(
        operationId = operationId,
        customerId = customerId,
        paymentMethodId = paymentMethodId,
        paymentOperationNo = paymentOperationNo,
        amountPaid = amountPaid,
        changeReturned = changeReturned
    )
}

fun Sale.toEntity(): SaleEntity {
    return SaleEntity(
        operationId = operationId,
        customerId = customerId,
        paymentMethodId = paymentMethodId,
        paymentOperationNo = paymentOperationNo,
        amountPaid = amountPaid,
        changeReturned = changeReturned
    )
}
