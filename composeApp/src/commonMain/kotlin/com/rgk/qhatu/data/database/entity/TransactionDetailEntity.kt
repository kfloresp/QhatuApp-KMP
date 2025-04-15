package com.rgk.qhatu.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_details")
data class TransactionDetailEntity(
    @PrimaryKey(autoGenerate = true)
    val idInternal: Int,
    val id: String,
    val movimiento_id: String,
    val producto_id: String,
    val cantidad: Int,
    val precio_unitario: Double,
    val subtotal: Double,
    val lote: String?,
    val fecha_vencimiento: Long?,
    val fecha_sincronizado: Long,
    val flag_sincronizado: Int
)
