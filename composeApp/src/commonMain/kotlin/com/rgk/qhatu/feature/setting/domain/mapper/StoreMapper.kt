package com.rgk.qhatu.feature.setting.domain.mapper

import com.rgk.qhatu.feature.setting.data.database.entity.StoreEntity
import com.rgk.qhatu.feature.setting.data.remote.model.StoreModel
import com.rgk.qhatu.feature.setting.domain.model.Store

fun StoreModel.toDomain(): Store = Store(
    id = id,
    companyName = companyName,
    commercialName = commercialName,
    ruc = ruc,
    address = address,
    phone = phone,
    isSynced = isSynced,
    lastUpdated = lastUpdated,
)

fun Store.toEntity(): StoreEntity = StoreEntity(
    id = id,
    companyName = companyName,
    commercialName = commercialName,
    ruc = ruc,
    address = address.orEmpty(),
    phone = phone.orEmpty(),
    isSynced = isSynced,
    lastUpdated = lastUpdated,
)

fun StoreEntity.toDomain(): Store = Store(
    id = id,
    companyName = companyName,
    commercialName = commercialName,
    ruc = ruc,
    address = address,
    phone = phone,
    isSynced = isSynced,
    lastUpdated = lastUpdated,
)

fun StoreEntity.toModel(): StoreModel = StoreModel(
    id = id,
    companyName = companyName,
    commercialName = commercialName,
    ruc = ruc,
    address = address,
    phone = phone,
    isSynced = isSynced,
    lastUpdated = lastUpdated,
)

fun StoreModel.toEntity(): StoreEntity = StoreEntity(
    id = id,
    companyName = companyName,
    commercialName = commercialName,
    ruc = ruc,
    address = address.orEmpty(),
    phone = phone.orEmpty(),
    isSynced = isSynced,
    lastUpdated = lastUpdated,
)