package com.rgk.qhatu.feature.setting.domain.mapper

import com.rgk.qhatu.feature.setting.data.database.entity.BrandEntity
import com.rgk.qhatu.feature.setting.data.remote.model.BrandModel
import com.rgk.qhatu.feature.setting.domain.model.Brand

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
