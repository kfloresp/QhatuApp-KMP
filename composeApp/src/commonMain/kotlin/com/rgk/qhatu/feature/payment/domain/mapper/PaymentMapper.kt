package com.rgk.qhatu.feature.payment.domain.mapper

import com.rgk.qhatu.feature.payment.data.database.entity.PaymentEntity
import com.rgk.qhatu.feature.payment.data.remote.model.PaymentModel
import com.rgk.qhatu.feature.payment.domain.model.Payment

fun PaymentModel.toDomain(): Payment = Payment(
    id = id,
    customerId = clientId,
    paymentDate = paymentDate,
    amountPaid = amountPaid.toString(),
    paymentMethodId = paymentMethodId,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun PaymentEntity.toDomain(): Payment = Payment(
    id = id,
    customerId = clientId,
    paymentDate = paymentDate,
    amountPaid = amountPaid.toString(),
    paymentMethodId = paymentMethodId,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun Payment.toEntity(): PaymentEntity = PaymentEntity(
    id = id,
    clientId = customerId,
    paymentDate = paymentDate,
    amountPaid = amountPaid.toDouble(),
    paymentMethodId = paymentMethodId,
    comments = comments,
    numberOperation = numberOperation,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun PaymentModel.toEntity(): PaymentEntity = PaymentEntity(
    id = id,
    clientId = clientId,
    paymentDate = paymentDate,
    amountPaid = amountPaid,
    paymentMethodId = paymentMethodId,
    comments = comments,
    numberOperation = numberOperation,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)

fun PaymentEntity.toModel(): PaymentModel = PaymentModel(
    id = id,
    clientId = clientId,
    paymentDate = paymentDate,
    amountPaid = amountPaid,
    paymentMethodId = paymentMethodId,
    comments = comments,
    numberOperation = numberOperation,
    isSynced = isSynced,
    isDeleted = isDeleted,
    lastUpdated = lastUpdated,
)