package com.rgk.qhatu.feature.setting.domain.mapper

import com.rgk.qhatu.feature.setting.data.database.entity.ConfigurationEntity
import com.rgk.qhatu.feature.setting.data.remote.model.ConfigurationModel
import com.rgk.qhatu.feature.setting.domain.model.Configuration

fun ConfigurationModel.toDomain(): Configuration = Configuration(
    id = id,
    type = type,
    name = name,
    description = description,
    order = order,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun Configuration.toEntity(): ConfigurationEntity = ConfigurationEntity(
    id = id,
    type = type,
    name = name,
    description = description.orEmpty(),
    order = order,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun ConfigurationEntity.toDomain(): Configuration = Configuration(
    id = id,
    type = type,
    name = name,
    description = description,
    order = order,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun ConfigurationEntity.toModel(): ConfigurationModel = ConfigurationModel(
    id = id,
    type = type,
    name = name,
    description = description,
    order = order,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun ConfigurationModel.toEntity(): ConfigurationEntity = ConfigurationEntity(
    id = id,
    type = type,
    name = name,
    description = description.orEmpty(),
    order = order,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

