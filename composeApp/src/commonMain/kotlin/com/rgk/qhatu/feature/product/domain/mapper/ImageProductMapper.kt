package com.rgk.qhatu.feature.product.domain.mapper

import com.rgk.qhatu.feature.product.data.database.entity.ImageProductEntity
import com.rgk.qhatu.feature.product.domain.model.ImageProduct

fun ImageProductEntity.toDomain(): ImageProduct = ImageProduct(
    id = id,
    productId = productId,
    filename = filename,
    syncedDate = syncedDate,
    isSynced = isSynced,
    lastUpdated = lastUpdated,
)

fun ImageProduct.toEntity(): ImageProductEntity = ImageProductEntity(
    id = id,
    productId = productId,
    filename = filename,
    syncedDate = syncedDate,
    isSynced = isSynced,
    lastUpdated = lastUpdated,
)