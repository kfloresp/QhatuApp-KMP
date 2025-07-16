package com.rgk.qhatu.data.feature.sale.remote.model
import kotlinx.serialization.Serializable

@Serializable
data class TransactionModel(
    val id: String,
    val tipo_id: String,
    val cliente_id: String? = null,
    val fecha: Long,
    val total: Double,
    val estado_id: String,
    val monto_pagado: Double = 0.0,
    val observaciones: String? = null,
    val fecha_sincronizado: Long = 0,
    val flag_sincronizado: Int = 0
)