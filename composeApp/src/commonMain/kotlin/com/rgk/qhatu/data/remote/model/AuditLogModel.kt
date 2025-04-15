package com.rgk.qhatu.data.remote.model
import kotlinx.serialization.Serializable

@Serializable
data class AuditLogModel(
    val id: String,
    val entidad: String,
    val entidad_id: String,
    val accion: String,
    val usuario: String? = null,
    val fecha: Long,
    val detalles: String? = null,
    val fecha_sincronizado: Long = 0,
    val flag_sincronizado: Int = 0
)