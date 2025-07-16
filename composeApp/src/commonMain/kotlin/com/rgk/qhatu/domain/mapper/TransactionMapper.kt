package com.rgk.qhatu.domain.mapper

import com.rgk.qhatu.data.sale.database.entity.TransactionEntity
import com.rgk.qhatu.data.sale.remote.model.TransactionModel
import com.rgk.qhatu.domain.model.Transaction

fun TransactionModel.toDomain(): Transaction = Transaction(
    id = id,
    tipo_id = tipo_id,
    cliente_id = cliente_id,
    fecha = fecha,
    total = total,
    estado_id = estado_id,
    monto_pagado = monto_pagado,
    observaciones = observaciones,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
fun Transaction.toEntity(): TransactionEntity = TransactionEntity(
    idInternal = 0,
    id = id,
    tipo_id = tipo_id,
    cliente_id = cliente_id,
    fecha = fecha,
    total = total,
    estado_id = estado_id,
    monto_pagado = monto_pagado,
    observaciones = observaciones,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun TransactionEntity.toDomain(): Transaction = Transaction(
    id = id,
    tipo_id = tipo_id,
    cliente_id = cliente_id,
    fecha = fecha,
    total = total,
    estado_id = estado_id,
    monto_pagado = monto_pagado,
    observaciones = observaciones,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)

fun TransactionEntity.toModel(): TransactionModel = TransactionModel(
    id = id,
    tipo_id = tipo_id,
    cliente_id = cliente_id,
    fecha = fecha,
    total = total,
    estado_id = estado_id,
    monto_pagado = monto_pagado,
    observaciones = observaciones,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
fun TransactionModel.toEntity(): TransactionEntity = TransactionEntity(
    idInternal = 0,
    id = id,
    tipo_id = tipo_id,
    cliente_id = cliente_id,
    fecha = fecha,
    total = total,
    estado_id = estado_id,
    monto_pagado = monto_pagado,
    observaciones = observaciones,
    fecha_sincronizado = fecha_sincronizado,
    flag_sincronizado = flag_sincronizado
)
