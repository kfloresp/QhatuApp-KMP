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
    lastUpdated = lastUpdated,
)

fun Store.toEntity(): StoreEntity = StoreEntity(
    id = id,
    name = name,
    address = address.orEmpty(),
    phone = phone.orEmpty(),
    logoUrl = logoUrl.orEmpty(),
    isSynced = isSynced,
    lastUpdated = lastUpdated,
)

fun StoreEntity.toDomain(): Store = Store(
    id = id,
    name = name,
    address = address,
    phone = phone,
    logoUrl = logoUrl,
    isSynced = isSynced,
    lastUpdated = lastUpdated,
)

fun StoreEntity.toModel(): StoreModel = StoreModel(
    id = id,
    name = name,
    address = address,
    phone = phone,
    logoUrl = logoUrl,
    isSynced = isSynced,
    lastUpdated = lastUpdated,
)

fun StoreModel.toEntity(): StoreEntity = StoreEntity(
    id = id,
    name = name,
    address = address.orEmpty(),
    phone = phone.orEmpty(),
    logoUrl = logoUrl.orEmpty(),
    isSynced = isSynced,
    lastUpdated = lastUpdated,
)