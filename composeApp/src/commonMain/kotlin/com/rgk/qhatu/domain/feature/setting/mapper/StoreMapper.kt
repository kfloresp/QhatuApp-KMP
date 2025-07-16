package com.rgk.qhatu.domain.feature.setting.mapper

import com.rgk.qhatu.data.feature.setting.database.entity.StoreEntity
import com.rgk.qhatu.data.feature.setting.remote.model.StoreModel
import com.rgk.qhatu.domain.feature.setting.model.Store

fun StoreModel.toDomain(): Store = Store(
    id = this.id,
    nombre = this.nombre,
    direccion = this.direccion,
    celular = this.celular,
    urlLogo = this.urlLogo,
    fechaSincronizado = this.fechaSincronizado
)

fun Store.toEntity(): StoreEntity = StoreEntity(
    idInternal = 0,
    id = this.id,
    nombre = this.nombre,
    direccion = this.direccion,
    celular = this.celular,
    urlLogo = this.urlLogo,
    flagSincronizado = 1,
    fechaSincronizado = this.fechaSincronizado
)

fun StoreEntity.toDomain(): Store = Store(
    id = this.id,
    nombre = this.nombre,
    direccion = this.direccion,
    celular = this.celular,
    urlLogo = this.urlLogo,
    fechaSincronizado = this.fechaSincronizado
)

fun StoreEntity.toModel(): StoreModel = StoreModel(
    id = this.id,
    nombre = this.nombre,
    direccion = this.direccion,
    celular = this.celular,
    urlLogo = this.urlLogo
)

fun StoreModel.toEntity(): StoreEntity = StoreEntity(
    idInternal = 0,
    id = this.id,
    nombre = this.nombre,
    direccion = this.direccion,
    celular = this.celular,
    urlLogo = this.urlLogo,
    flagSincronizado = 1,
    fechaSincronizado = this.fechaSincronizado
)