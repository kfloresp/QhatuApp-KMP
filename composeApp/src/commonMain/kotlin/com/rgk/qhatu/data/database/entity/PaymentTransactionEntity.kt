package com.rgk.qhatu.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payment_transactions")
data class PaymentTransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val idInternal: Int,
    val id: String,
    val pago_id: String,
    val movimiento_id: String,
    val monto_aplicado: Double,
    val fecha_sincronizado: Long,
    val flag_sincronizado: Int
)
