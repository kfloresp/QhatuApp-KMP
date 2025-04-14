package com.rgk.qhatu.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class PaymentTransaction(
    val id: String,
    val pago_id: String,
    val movimiento_id: String,
    val monto_aplicado: Double,
    val fecha_sincronizado: Long = 0,
    val flag_sincronizado: Int = 0
)