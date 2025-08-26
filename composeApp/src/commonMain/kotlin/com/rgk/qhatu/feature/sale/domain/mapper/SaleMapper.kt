package com.rgk.qhatu.feature.sale.domain.mapper

import com.rgk.qhatu.feature.sale.data.database.entity.SaleEntity
import com.rgk.qhatu.feature.sale.domain.model.Sale
import com.rgk.qhatu.feature.sale.domain.model.SaleVaucherType

fun SaleEntity.toDomain(): Sale {
    return Sale(
        operationId = operationId,
        customerId = customerId,
        vaucherType = SaleVaucherType.entries.first { it.value == vaucherType },
        vaucherOperationNo = vaucherOperationNo,
        subtotalWithIGV = subtotalWithIGV,
        totalDiscounts = totalDiscounts,
        grandTotal = grandTotal,
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
        vaucherType = vaucherType.value,
        vaucherOperationNo = vaucherOperationNo,
        subtotalWithIGV = subtotalWithIGV,
        totalDiscounts = totalDiscounts,
        grandTotal = grandTotal,
        paymentMethodId = paymentMethodId,
        paymentOperationNo = paymentOperationNo,
        amountPaid = amountPaid,
        changeReturned = changeReturned
    )
}
