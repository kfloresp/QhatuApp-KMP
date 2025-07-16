package com.rgk.qhatu.domain.feature.setting.mapper

import com.rgk.qhatu.data.feature.setting.database.entity.BrandEntity
import com.rgk.qhatu.data.feature.setting.remote.model.BrandModel
import com.rgk.qhatu.domain.feature.setting.model.Brand

fun BrandModel.toDomain(): Brand = Brand(
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    flag_sincronizado = flag_sincronizado,
    fecha_actualizacion = fecha_actualizacion,
)
fun Brand.toEntity(): BrandEntity = BrandEntity(
    idInternal = 0,
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    flag_sincronizado = flag_sincronizado,
    fecha_actualizacion = fecha_actualizacion
)

fun BrandEntity.toDomain(): Brand = Brand(
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    flag_sincronizado = flag_sincronizado,
    fecha_actualizacion = fecha_actualizacion
)

fun BrandEntity.toModel(): BrandModel = BrandModel(
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    flag_sincronizado = flag_sincronizado,
    fecha_actualizacion = fecha_actualizacion
)
fun BrandModel.toEntity(): BrandEntity = BrandEntity(
    idInternal = 0,
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    flag_sincronizado = flag_sincronizado,
    fecha_actualizacion = fecha_actualizacion
)
