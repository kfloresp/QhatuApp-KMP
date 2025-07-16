package com.rgk.qhatu.feature.setting.domain.mapper

import com.rgk.qhatu.feature.setting.data.database.entity.CategoryEntity
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.data.remote.model.CategoryModel

fun CategoryModel.toDomain(): Category = Category(
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    flag_sincronizado = flag_sincronizado,
    fecha_actualizacion = fecha_actualizacion
)
fun Category.toEntity(): CategoryEntity = CategoryEntity(
    idInternal = 0,
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    flag_sincronizado = flag_sincronizado,
    fecha_actualizacion = fecha_actualizacion
)

fun CategoryEntity.toDomain(): Category = Category(
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    flag_sincronizado = flag_sincronizado,
    fecha_actualizacion = fecha_actualizacion
)

fun CategoryEntity.toModel(): CategoryModel = CategoryModel(
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    flag_sincronizado = flag_sincronizado,
    fecha_actualizacion = fecha_actualizacion
)
fun CategoryModel.toEntity(): CategoryEntity = CategoryEntity(
    idInternal = 0,
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    flag_sincronizado = flag_sincronizado,
    fecha_actualizacion = fecha_actualizacion
)