package com.rgk.qhatu.domain.feature.setting.mapper

import com.rgk.qhatu.data.feature.setting.database.entity.ConfigurationEntity
import com.rgk.qhatu.data.feature.setting.remote.model.ConfigurationModel
import com.rgk.qhatu.domain.feature.setting.model.Configuration

fun ConfigurationModel.toDomain(): Configuration = Configuration(
    id = id,
    tipo = tipo,
    nombre = nombre,
    descripcion = descripcion,
    orden = orden,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun Configuration.toEntity(): ConfigurationEntity = ConfigurationEntity(
    idInternal = 0,
    id = id,
    tipo = tipo,
    nombre = nombre,
    descripcion = descripcion,
    orden = orden,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun ConfigurationEntity.toDomain(): Configuration = Configuration(
    id = id,
    tipo = tipo,
    nombre = nombre,
    descripcion = descripcion,
    orden = orden,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun ConfigurationEntity.toModel(): ConfigurationModel = ConfigurationModel(
    id = id,
    tipo = tipo,
    nombre = nombre,
    descripcion = descripcion,
    orden = orden,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun ConfigurationModel.toEntity(): ConfigurationEntity = ConfigurationEntity(
    idInternal = 0,
    id = id,
    tipo = tipo,
    nombre = nombre,
    descripcion = descripcion,
    orden = orden,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

