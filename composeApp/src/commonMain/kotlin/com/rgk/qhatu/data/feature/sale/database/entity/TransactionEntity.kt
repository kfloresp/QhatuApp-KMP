package com.rgk.qhatu.data.feature.sale.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val idInternal: Int,
    val id: String,
    val tipo_id: String,
    val cliente_id: String?,
    val fecha: Long,
    val total: Double,
    val estado_id: String,
    val monto_pagado: Double,
    val observaciones: String?,
    val fecha_sincronizado: Long,
    val flag_sincronizado: Int
)
