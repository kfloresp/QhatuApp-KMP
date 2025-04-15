package com.rgk.qhatu.domain.mapper

import com.rgk.qhatu.data.database.entity.TransactionDetailEntity
import com.rgk.qhatu.data.remote.model.TransactionDetailModel
import com.rgk.qhatu.domain.model.TransactionDetail

fun TransactionDetailModel.toDomain(): TransactionDetail = TransactionDetail(
    id = id,
    movimiento_id = movimiento_id,
    producto_id = producto_id,
    cantidad = cantidad,
    precio_unitario = precio_unitario,
    subtotal = subtotal,
    lote = lote,
    fecha_vencimiento = fecha_vencimiento,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
fun TransactionDetail.toEntity(): TransactionDetailEntity = TransactionDetailEntity(
    idInternal = 0,
    id = id,
    movimiento_id = movimiento_id,
    producto_id = producto_id,
    cantidad = cantidad,
    precio_unitario = precio_unitario,
    subtotal = subtotal,
    lote = lote,
    fecha_vencimiento = fecha_vencimiento,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun TransactionDetailEntity.toDomain(): TransactionDetail = TransactionDetail(
    id = id,
    movimiento_id = movimiento_id,
    producto_id = producto_id,
    cantidad = cantidad,
    precio_unitario = precio_unitario,
    subtotal = subtotal,
    lote = lote,
    fecha_vencimiento = fecha_vencimiento,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun TransactionDetailEntity.toModel(): TransactionDetailModel = TransactionDetailModel(
    id = id,
    movimiento_id = movimiento_id,
    producto_id = producto_id,
    cantidad = cantidad,
    precio_unitario = precio_unitario,
    subtotal = subtotal,
    lote = lote,
    fecha_vencimiento = fecha_vencimiento,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
fun TransactionDetailModel.toEntity(): TransactionDetailEntity = TransactionDetailEntity(
    idInternal = 0,
    id = id,
    movimiento_id = movimiento_id,
    producto_id = producto_id,
    cantidad = cantidad,
    precio_unitario = precio_unitario,
    subtotal = subtotal,
    lote = lote,
    fecha_vencimiento = fecha_vencimiento,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
