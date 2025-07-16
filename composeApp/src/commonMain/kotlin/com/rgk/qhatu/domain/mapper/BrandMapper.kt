package com.rgk.qhatu.domain.mapper

import com.rgk.qhatu.data.setting.database.entity.BrandEntity
import com.rgk.qhatu.data.setting.remote.model.BrandModel
import com.rgk.qhatu.domain.model.Brand

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
