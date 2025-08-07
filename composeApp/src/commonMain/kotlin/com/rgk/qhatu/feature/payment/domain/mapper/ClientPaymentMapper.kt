package com.rgk.qhatu.feature.payment.domain.mapper

import com.rgk.qhatu.feature.payment.data.database.entity.ClientPaymentEntity
import com.rgk.qhatu.feature.payment.data.remote.model.ClientPaymentModel
import com.rgk.qhatu.feature.payment.domain.model.ClientPayment

fun ClientPaymentModel.toDomain(): ClientPayment = ClientPayment(
    id = id,
    clientId = clientId,
    paymentDate = paymentDate,
    amountPaid = amountPaid,
    paymentMethodId = paymentMethodId,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun ClientPaymentEntity.toDomain(): ClientPayment = ClientPayment(
    id = id,
    clientId = clientId,
    paymentDate = paymentDate,
    amountPaid = amountPaid,
    paymentMethodId = paymentMethodId,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun ClientPayment.toEntity(): ClientPaymentEntity = ClientPaymentEntity(
    id = id,
    clientId = clientId,
    paymentDate = paymentDate,
    amountPaid = amountPaid,
    paymentMethodId = paymentMethodId,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun ClientPaymentModel.toEntity(): ClientPaymentEntity = ClientPaymentEntity(
    id = id,
    clientId = clientId,
    paymentDate = paymentDate,
    amountPaid = amountPaid,
    paymentMethodId = paymentMethodId,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun ClientPaymentEntity.toModel(): ClientPaymentModel = ClientPaymentModel(
    id = id,
    clientId = clientId,
    paymentDate = paymentDate,
    amountPaid = amountPaid,
    paymentMethodId = paymentMethodId,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)