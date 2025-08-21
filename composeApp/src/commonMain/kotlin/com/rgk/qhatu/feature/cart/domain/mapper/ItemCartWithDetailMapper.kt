package com.rgk.qhatu.feature.cart.domain.mapper

import com.rgk.qhatu.feature.cart.data.database.entity.CartItemWithDetail
import com.rgk.qhatu.feature.cart.domain.model.CartItemDetail

fun CartItemWithDetail.toDomain(): CartItemDetail = CartItemDetail(
    id = id,
    ean = ean,
    name = name,
    categoryId = categoryId,
    category = category,
    storageTypeId = storageTypeId,
    storageType = storageType,
    brandId = brandId,
    brand = brand,
    unitPrice = unitPrice,
    unitMeasureId = unitMeasureId,
    unitMeasure = unitMeasure,
    isBatch = isBatch,
    isActive = isActive,
    idCart = idCart,
    quantityCart = quantityCart,
    unitPriceCart = unitPriceCart,
    totalPriceCart = totalPriceCart
)