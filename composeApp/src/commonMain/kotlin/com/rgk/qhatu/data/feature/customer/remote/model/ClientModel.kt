package com.rgk.qhatu.data.feature.customer.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClientModel(
    val id: String,
    val nombre: String? = null,
    @SerialName("apellido_paterno") val apellidoPaterno: String? = null,
    @SerialName("apellido_materno") val apellidoMaterno: String? = null,
    @SerialName("tipo_documento") val tipoDocumento: String,
    @SerialName("numero_documento") val numeroDocumento: String,
    val celular: String? = null,
    val direccion: String? = null,
    val correo: String? = null,
    @SerialName("saldo_pendiente") val saldoPendiente: Double? = 0.0,
    @SerialName("flag_proveedor") val flagProveedor: Int = 0,
    @SerialName("flag_activo") val flagActivo: Int = 1,
    @SerialName("razon_social") val razonSocial: String? = null
)