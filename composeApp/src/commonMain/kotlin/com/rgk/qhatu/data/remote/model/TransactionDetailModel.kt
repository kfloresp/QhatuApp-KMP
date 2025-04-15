package com.rgk.qhatu.data.remote.model
import kotlinx.serialization.Serializable

@Serializable
data class TransactionDetailModel(
    val id: String,
    val movimiento_id: String,
    val producto_id: String,
    val cantidad: Int,
    val precio_unitario: Double,
    val subtotal: Double,
    val lote: String? = null,
    val fecha_vencimiento: Long? = null,
    val fecha_sincronizado: Long = 0,
    val flag_sincronizado: Int = 0
)