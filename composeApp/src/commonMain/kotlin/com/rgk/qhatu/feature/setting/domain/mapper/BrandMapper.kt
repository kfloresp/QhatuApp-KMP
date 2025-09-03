package com.rgk.qhatu.feature.setting.domain.mapper

import com.rgk.qhatu.feature.setting.data.database.entity.BrandEntity
import com.rgk.qhatu.feature.setting.data.remote.model.BrandModel
import com.rgk.qhatu.feature.setting.domain.model.Brand

fun BrandModel.toDomain(): Brand = Brand(
    id = id,
    name = name,
    isSynced = isSynced,
    lastUpdated = lastUpdated,
)

fun Brand.toEntity(): BrandEntity = BrandEntity(
    id = id,
    name = name,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated
)

fun BrandEntity.toDomain(): Brand = Brand(
    id = id,
    name = name,
    isSynced = isSynced,
    lastUpdated = lastUpdated
)

fun BrandEntity.toModel(): BrandModel = BrandModel(
    id = id,
    name = name,
    isSynced = isSynced,
    lastUpdated = lastUpdated
)

fun BrandModel.toEntity(): BrandEntity = BrandEntity(
    id = id,
    name = name,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated
)
