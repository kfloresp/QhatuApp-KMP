package com.rgk.qhatu.feature.product.domain.mapper

import com.rgk.qhatu.feature.product.data.database.entity.ProductEntity
import com.rgk.qhatu.feature.product.data.database.entity.ProductWithDetail
import com.rgk.qhatu.feature.product.data.remote.model.ProductModel
import com.rgk.qhatu.feature.product.domain.model.Product

fun ProductModel.toDomain(): Product = Product(
    id = id,
    ean = ean,
    name = name,
    categoryId = categoryId,
    storageTypeId = storageTypeId,
    brandId = brandId,
    unitPrice = unitPrice.toString(),
    unitMeasureId = unitMeasureId,
    isBatch = isBatch,
    isActive = isActive,
    syncedDate = syncedDate,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun Product.toEntity(): ProductEntity = ProductEntity(
    id = id,
    ean = ean,
    name = name,
    categoryId = categoryId,
    storageTypeId = storageTypeId,
    brandId = brandId,
    unitPrice = unitPrice.toDouble(),
    unitMeasureId = unitMeasureId,
    isBatch = isBatch,
    isActive = isActive,
    syncedDate = syncedDate,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun ProductEntity.toDomain(): Product = Product(
    id = id,
    ean = ean,
    name = name,
    categoryId = categoryId,
    storageTypeId = storageTypeId,
    brandId = brandId,
    unitPrice = unitPrice.toString(),
    unitMeasureId = unitMeasureId,
    isBatch = isBatch,
    isActive = isActive,
    syncedDate = syncedDate,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun ProductWithDetail.toDomain(): Product = Product(
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
    syncedDate = syncedDate,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun ProductEntity.toModel(): ProductModel = ProductModel(
    id = id,
    ean = ean,
    name = name,
    categoryId = categoryId,
    storageTypeId = storageTypeId,
    brandId = brandId,
    unitPrice = unitPrice,
    unitMeasureId = unitMeasureId,
    isBatch = isBatch,
    isActive = isActive,
    syncedDate = syncedDate,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun ProductModel.toEntity(): ProductEntity = ProductEntity(
    id = id,
    ean = ean,
    name = name,
    categoryId = categoryId,
    storageTypeId = storageTypeId,
    brandId = brandId,
    unitPrice = unitPrice,
    unitMeasureId = unitMeasureId,
    isBatch = isBatch,
    isActive = isActive,
    syncedDate = syncedDate,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)