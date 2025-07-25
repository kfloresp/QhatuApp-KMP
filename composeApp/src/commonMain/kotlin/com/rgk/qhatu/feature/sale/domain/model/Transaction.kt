package com.rgk.qhatu.feature.sale.domain.model

data class Transaction(
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