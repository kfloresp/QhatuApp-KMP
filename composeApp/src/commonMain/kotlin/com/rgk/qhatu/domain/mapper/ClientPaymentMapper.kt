package com.rgk.qhatu.domain.mapper

import com.rgk.qhatu.data.payment.database.entity.ClientPaymentEntity
import com.rgk.qhatu.data.payment.remote.model.ClientPaymentModel
import com.rgk.qhatu.domain.model.ClientPayment

fun ClientPaymentModel.toDomain(): ClientPayment = ClientPayment(
    id = id,
    cliente_id = cliente_id,
    fecha_pago = fecha_pago,
    monto_pagado = monto_pagado,
    metodo_pago_id = metodo_pago_id,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
fun ClientPayment.toEntity(): ClientPaymentEntity = ClientPaymentEntity(
    idInternal = 0,
    id = id,
    cliente_id = cliente_id,
    fecha_pago = fecha_pago,
    monto_pagado = monto_pagado,
    metodo_pago_id = metodo_pago_id,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun ClientPaymentEntity.toDomain(): ClientPayment = ClientPayment(
    id = id,
    cliente_id = cliente_id,
    fecha_pago = fecha_pago,
    monto_pagado = monto_pagado,
    metodo_pago_id = metodo_pago_id,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun ClientPaymentEntity.toModel(): ClientPaymentModel = ClientPaymentModel(
    id = id,
    cliente_id = cliente_id,
    fecha_pago = fecha_pago,
    monto_pagado = monto_pagado,
    metodo_pago_id = metodo_pago_id,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
fun ClientPaymentModel.toEntity(): ClientPaymentEntity = ClientPaymentEntity(
    idInternal = 0,
    id = id,
    cliente_id = cliente_id,
    fecha_pago = fecha_pago,
    monto_pagado = monto_pagado,
    metodo_pago_id = metodo_pago_id,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
