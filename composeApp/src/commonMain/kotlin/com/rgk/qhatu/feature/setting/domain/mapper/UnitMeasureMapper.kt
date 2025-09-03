package com.rgk.qhatu.feature.setting.domain.mapper

import com.rgk.qhatu.feature.setting.data.database.entity.UnitMeasureEntity
import com.rgk.qhatu.feature.setting.data.remote.model.UnitMeasureModel
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure

fun UnitMeasureModel.toDomain(): UnitMeasure = UnitMeasure(
    id = id,
    name = name,
    abbreviation = abbreviation,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun UnitMeasure.toEntity(): UnitMeasureEntity = UnitMeasureEntity(
    id = id,
    name = name,
    abbreviation = abbreviation.orEmpty(),
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun UnitMeasureEntity.toDomain(): UnitMeasure = UnitMeasure(
    id = id,
    name = name,
    abbreviation = abbreviation,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun UnitMeasureEntity.toModel(): UnitMeasureModel = UnitMeasureModel(
    id = id,
    name = name,
    abbreviation = abbreviation,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun UnitMeasureModel.toEntity(): UnitMeasureEntity = UnitMeasureEntity(
    id = id,
    name = name,
    abbreviation = abbreviation.orEmpty(),
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)
