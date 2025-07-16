package com.rgk.qhatu.data.audit.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audit_logs")
data class AuditLogEntity(
    @PrimaryKey(autoGenerate = true)
    val idInternal: Int,
    val id: String,
    val entidad: String,
    val entidad_id: String,
    val accion: String,
    val usuario: String?,
    val fecha: Long,
    val detalles: String?,
    val fecha_sincronizado: Long,
    val flag_sincronizado: Int
)