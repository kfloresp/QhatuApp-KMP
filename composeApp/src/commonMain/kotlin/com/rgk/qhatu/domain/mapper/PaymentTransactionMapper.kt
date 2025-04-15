package com.rgk.qhatu.domain.mapper

import com.rgk.qhatu.data.database.entity.PaymentTransactionEntity
import com.rgk.qhatu.data.remote.model.PaymentTransactionModel
import com.rgk.qhatu.domain.model.PaymentTransaction

fun PaymentTransactionModel.toDomain(): PaymentTransaction = PaymentTransaction(
    id = id,
    pago_id = pago_id,
    movimiento_id = movimiento_id,
    monto_aplicado = monto_aplicado,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
fun PaymentTransaction.toEntity(): PaymentTransactionEntity = PaymentTransactionEntity(
    idInternal = 0,
    id = id,
    pago_id = pago_id,
    movimiento_id = movimiento_id,
    monto_aplicado = monto_aplicado,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun PaymentTransactionEntity.toDomain(): PaymentTransaction = PaymentTransaction(
    id = id,
    pago_id = pago_id,
    movimiento_id = movimiento_id,
    monto_aplicado = monto_aplicado,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun PaymentTransactionEntity.toModel(): PaymentTransactionModel = PaymentTransactionModel(
    id = id,
    pago_id = pago_id,
    movimiento_id = movimiento_id,
    monto_aplicado = monto_aplicado,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
fun PaymentTransactionModel.toEntity(): PaymentTransactionEntity = PaymentTransactionEntity(
    idInternal = 0,
    id = id,
    pago_id = pago_id,
    movimiento_id = movimiento_id,
    monto_aplicado = monto_aplicado,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
