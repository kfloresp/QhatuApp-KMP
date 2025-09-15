package com.rgk.qhatu.feature.customer.domain.mapper

import com.rgk.qhatu.feature.customer.data.database.entity.CustomerEntity
import com.rgk.qhatu.feature.customer.data.remote.model.CustomerModel
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.model.DocumentType

fun CustomerEntity.toDomain(): Customer {
    return Customer(
        customerId = customerId,
        documentType = DocumentType.entries.first{ it.name == documentType},
        documentNumber = documentNumber,
        phoneNumber = phoneNumber,
        address = address,
        email = email,
        pendingAmount = pendingAmount,
        pendingAmountMax = pendingAmountMax,
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
        documentType = documentType.name,
        documentNumber = documentNumber,
        phoneNumber = phoneNumber,
        address = address,
        email = email,
        pendingAmount = pendingAmount,
        pendingAmountMax = pendingAmountMax,
        isSupplier = isSupplier,
        isActive = isActive,
        isSynced = isSynced,
        isDeleted = isDeleted,
        lastUpdated = lastUpdated
    )
}