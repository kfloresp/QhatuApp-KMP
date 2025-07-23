package com.rgk.qhatu.feature.setting.domain.mapper

import com.rgk.qhatu.feature.setting.data.database.entity.StoreEntity
import com.rgk.qhatu.feature.setting.data.remote.model.StoreModel
import com.rgk.qhatu.feature.setting.domain.model.Store

fun StoreModel.toDomain(): Store = Store(
    id = id,
    name = name,
    address = address,
    phone = phone,
    logoUrl = logoUrl,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun Store.toEntity(): StoreEntity = StoreEntity(
    id = id,
    name = name.orEmpty(),
    address = address.orEmpty(),
    phone = phone.orEmpty(),
    logoUrl = logoUrl.orEmpty(),
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun StoreEntity.toDomain(): Store = Store(
    id = id,
    name = name,
    address = address,
    phone = phone,
    logoUrl = logoUrl,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun StoreEntity.toModel(): StoreModel = StoreModel(
    id = id,
    name = name,
    address = address,
    phone = phone,
    logoUrl = logoUrl,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun StoreModel.toEntity(): StoreEntity = StoreEntity(
    id = id,
    name = name.orEmpty(),
    address = address.orEmpty(),
    phone = phone.orEmpty(),
    logoUrl = logoUrl.orEmpty(),
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)