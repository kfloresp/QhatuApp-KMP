package com.rgk.qhatu.feature.payment.data.remote.model
import kotlinx.serialization.Serializable

@Serializable
data class ClientPaymentModel(
    val id: String,
    val cliente_id: String,
    val fecha_pago: Long,
    val monto_pagado: Double,
    val metodo_pago_id: String? = null,
    val fecha_sincronizado: Long = 0,
    val flag_sincronizado: Int = 0
)