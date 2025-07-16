package com.rgk.qhatu.domain.feature.customer.model

data class Client(
    val id: String,
    val nombre: String? = null,
    val razonSocial: String? = null,
    val apellidoPaterno: String? = null,
    val apellidoMaterno: String? = null,
    val tipoDocumento: String,
    val numeroDocumento: String,
    val celular: String? = null,
    val direccion: String? = null,
    val correo: String? = null,
    val saldoPendiente: Double? = 0.0,
    val flagProveedor: Int = 0,
    val flagActivo: Int = 1,
    val fechaSincronizado: Long = 0,
    val flagSincronizado: Int = 0
)