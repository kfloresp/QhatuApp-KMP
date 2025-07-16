package com.rgk.qhatu.data.payment.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client_payments")
data class ClientPaymentEntity(
    @PrimaryKey(autoGenerate = true)
    val idInternal: Int,
    val id: String,
    val cliente_id: String,
    val fecha_pago: Long,
    val monto_pagado: Double,
    val metodo_pago_id: String?,
    val fecha_sincronizado: Long,
    val flag_sincronizado: Int
)
