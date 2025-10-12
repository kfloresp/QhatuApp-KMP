package com.rgk.qhatu.feature.customer.domain.mapper

import com.rgk.qhatu.feature.customer.data.database.entity.CustomerFlowEntity
import com.rgk.qhatu.feature.customer.domain.model.CustomerFlow

fun CustomerFlow.toEntity(): CustomerFlowEntity {
    return CustomerFlowEntity(
        customerFlowId = customerFlowId,
        customerId = customerId,
        operationId = operationId,
        typeFlow = typeFlow,
        dateRegister = dateRegister,
        amountRegister = amountRegister,
        isActive = isActive,
        isSynced = isSynced,
        isDeleted = isDeleted,
        lastUpdated = lastUpdated
    )
}

fun CustomerFlowEntity.toDomain(): CustomerFlow {
    return CustomerFlow(
        customerFlowId = customerFlowId,
        customerId = customerId,
        operationId = operationId,
        typeFlow = typeFlow,
        dateRegister = dateRegister,
        amountRegister = amountRegister,
        isActive = isActive,
        isSynced = isSynced,
        isDeleted = isDeleted,
        lastUpdated = lastUpdated
    )
}