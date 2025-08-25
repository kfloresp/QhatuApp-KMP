package com.rgk.qhatu.feature.purchase.domain.mapper

import com.rgk.qhatu.feature.purchase.data.database.entity.PurchaseEntity
import com.rgk.qhatu.feature.purchase.domain.model.Purchase

fun PurchaseEntity.toDomain(): Purchase =
    Purchase(
        operationId = operationId,
        supplierId = supplierId,
        documentRef = documentRef,
        paymentTerms = paymentTerms
    )

fun Purchase.toEntity(): PurchaseEntity =
    PurchaseEntity(
        operationId = operationId,
        supplierId = supplierId,
        documentRef = documentRef,
        paymentTerms = paymentTerms
    )
