package com.rgk.qhatu.feature.customer.domain.mapper

import com.rgk.qhatu.common.util.orZero
import com.rgk.qhatu.feature.customer.data.database.entity.CustomerEntity
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.model.DocumentType

fun CustomerEntity.toDomain(): Customer {
    return Customer(
        customerId = customerId,
        documentType = DocumentType.entries.first{ it.value == documentType},
        documentNumber = documentNumber,
        phoneNumber = phoneNumber,
        address = address,
        email = email,
        pendingAmount = pendingAmount,
        pendingAmountMax = pendingAmountMax.toString(),
        isSupplier = isSupplier,
        isActive = isActive,
        isSynced = isSynced,
        isDeleted = isDeleted,
        lastUpdated = lastUpdated
    )
}

fun Customer.toEntity(): CustomerEntity {
    return CustomerEntity(
        customerId = customerId,
        documentType = documentType.value,
        documentNumber = documentNumber,
        phoneNumber = phoneNumber,
        address = address,
        email = email,
        pendingAmount = pendingAmount,
        pendingAmountMax = pendingAmountMax?.toDouble().orZero(),
        isSupplier = isSupplier,
        isActive = isActive,
        isSynced = isSynced,
        isDeleted = isDeleted,
        lastUpdated = lastUpdated
    )
}