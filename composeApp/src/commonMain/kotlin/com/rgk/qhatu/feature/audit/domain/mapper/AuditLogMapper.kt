package com.rgk.qhatu.feature.audit.domain.mapper

import com.rgk.qhatu.feature.audit.data.database.entity.AuditLogEntity
import com.rgk.qhatu.feature.audit.data.remote.model.AuditLogModel
import com.rgk.qhatu.feature.audit.domain.model.AuditLog

fun AuditLogModel.toDomain(): AuditLog = AuditLog(
    id = id,
    entidad = entidad,
    entidad_id = entidad_id,
    accion = accion,
    usuario = usuario,
    fecha = fecha,
    detalles = detalles,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
fun AuditLog.toEntity(): AuditLogEntity = AuditLogEntity(
    idInternal = 0,
    id = id,
    entidad = entidad,
    entidad_id = entidad_id,
    accion = accion,
    usuario = usuario,
    fecha = fecha,
    detalles = detalles,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun AuditLogEntity.toDomain(): AuditLog = AuditLog(
    id = id,
    entidad = entidad,
    entidad_id = entidad_id,
    accion = accion,
    usuario = usuario,
    fecha = fecha,
    detalles = detalles,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun AuditLogEntity.toModel(): AuditLogModel = AuditLogModel(
    id = id,
    entidad = entidad,
    entidad_id = entidad_id,
    accion = accion,
    usuario = usuario,
    fecha = fecha,
    detalles = detalles,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
fun AuditLogModel.toEntity(): AuditLogEntity = AuditLogEntity(
    idInternal = 0,
    id = id,
    entidad = entidad,
    entidad_id = entidad_id,
    accion = accion,
    usuario = usuario,
    fecha = fecha,
    detalles = detalles,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
