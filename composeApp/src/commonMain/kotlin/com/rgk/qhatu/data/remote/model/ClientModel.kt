package com.rgk.qhatu.data.remote.model
import kotlinx.serialization.Serializable

@Serializable
data class ClientModel(
    val id: String,
    val nombre: String,
    val apellido_paterno: String,
    val apellido_materno: String,
    val tipo_documento: String? = null,
    val numero_documento: String? = null,
    val celular: String? = null,
    val direccion: String? = null,
    val correo: String? = null,
    val saldo_pendiente: Double = 0.0,
    val flag_proveedor: Int = 0,
    val flag_activo: Int = 1,
    val fecha_sincronizado: Long = 0,
    val flag_sincronizado: Int = 0
)