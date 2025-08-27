package com.rgk.qhatu.feature.sale.domain.mapper

import com.rgk.qhatu.common.util.orZero
import com.rgk.qhatu.feature.sale.data.database.entity.SaleEntity
import com.rgk.qhatu.feature.sale.domain.model.Sale
import com.rgk.qhatu.feature.sale.domain.model.SalePaymentMethod
import com.rgk.qhatu.feature.sale.domain.model.SaleVoucherType

fun SaleEntity.toDomain(): Sale {
    return Sale(
        operationId = operationId,
        customerId = customerId,
        voucherType = SaleVoucherType.entries.first { it.value == voucherType },
        voucherOperationNo = voucherOperationNo,
        subtotalWithIGV = subtotalWithIGV,
        totalDiscounts = totalDiscounts,
        grandTotal = grandTotal,
        paymentMethod = SalePaymentMethod.entries.first { it.value == paymentMethod },
        paymentOperationNo = paymentOperationNo,
        amountPaid = amountPaid.toString(),
        changeReturned = changeReturned
    )
}

fun Sale.toEntity(): SaleEntity {
    return SaleEntity(
        operationId = operationId,
        customerId = customerId,
        voucherType = voucherType.value,
        voucherOperationNo = voucherOperationNo,
        subtotalWithIGV = subtotalWithIGV,
        totalDiscounts = totalDiscounts,
        grandTotal = grandTotal,
        paymentMethod = paymentMethod.value,
        paymentOperationNo = paymentOperationNo,
        amountPaid = amountPaid?.toDouble().orZero(),
        changeReturned = changeReturned
    )
}
