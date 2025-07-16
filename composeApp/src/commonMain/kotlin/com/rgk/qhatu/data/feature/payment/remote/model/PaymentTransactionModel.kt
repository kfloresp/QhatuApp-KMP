package com.rgk.qhatu.data.feature.payment.remote.model
import kotlinx.serialization.Serializable

@Serializable
data class PaymentTransactionModel(
    val id: String,
    val pago_id: String,
    val movimiento_id: String,
    val monto_aplicado: Double,
    val fecha_sincronizado: Long = 0,
    val flag_sincronizado: Int = 0
)