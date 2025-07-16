package com.rgk.qhatu.feature.setting.domain.mapper

import com.rgk.qhatu.feature.setting.data.database.entity.UnitMeasureEntity
import com.rgk.qhatu.feature.setting.data.remote.model.UnitMeasureModel
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure

fun UnitMeasureModel.toDomain(): UnitMeasure = UnitMeasure(
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    abreviatura = abreviatura,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun UnitMeasure.toEntity(): UnitMeasureEntity = UnitMeasureEntity(
    idInternal = 0,
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    abreviatura = abreviatura,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun UnitMeasureEntity.toDomain(): UnitMeasure = UnitMeasure(
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    abreviatura = abreviatura,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun UnitMeasureEntity.toModel(): UnitMeasureModel = UnitMeasureModel(
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    abreviatura = abreviatura,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun UnitMeasureModel.toEntity(): UnitMeasureEntity = UnitMeasureEntity(
    idInternal = 0,
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    abreviatura = abreviatura,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
