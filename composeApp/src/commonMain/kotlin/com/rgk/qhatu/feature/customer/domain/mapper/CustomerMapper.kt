package com.rgk.qhatu.feature.customer.domain.mapper

import com.rgk.qhatu.feature.customer.data.database.entity.CustomerEntity
import com.rgk.qhatu.feature.customer.data.remote.model.CustomerModel
import com.rgk.qhatu.feature.customer.domain.model.Customer

fun CustomerModel.toEntity(): CustomerEntity {
    return CustomerEntity(
        id = id,
        businessName = businessName,
        firstName = firstName,
        lastName = lastName,
        motherLastName = motherLastName,
        documentType = documentType,
        documentNumber = documentNumber,
        phoneNumber = phoneNumber,
        address = address,
        email = email,
        pendingAmount = pendingAmount,
        isSupplier = isSupplier,
        isActive = isActive,
        isSynced = isSynced,
        isDeleted = isDeleted,
        lastUpdated = lastUpdated
    )
}

fun CustomerModel.toDomain(): Customer {
    return Customer(
        id = id,
        businessName = businessName,
        firstName = firstName,
        lastName = lastName,
        motherLastName = motherLastName,
        documentType = documentType,
        documentNumber = documentNumber,
        phoneNumber = phoneNumber,
        address = address,
        email = email,
        pendingAmount = pendingAmount,
        isSupplier = isSupplier,
        isActive = isActive,
        isSynced = isSynced,
        isDeleted = isDeleted,
        lastUpdated = lastUpdated
    )
}

fun CustomerEntity.toDomain(): Customer {
    return Customer(
        id = id,
        businessName = businessName,
        firstName = firstName,
        lastName = lastName,
        motherLastName = motherLastName,
        documentType = documentType,
        documentNumber = documentNumber,
        phoneNumber = phoneNumber,
        address = address,
        email = email,
        pendingAmount = pendingAmount,
        isSupplier = isSupplier,
        isActive = isActive,
        isSynced = isSynced,
        isDeleted = isDeleted,
        lastUpdated = lastUpdated
    )
}

fun Customer.toEntity(): CustomerEntity {
    return CustomerEntity(
        id = id,
        businessName = businessName,
        firstName = firstName,
        lastName = lastName,
        motherLastName = motherLastName,
        documentType = documentType,
        documentNumber = documentNumber,
        phoneNumber = phoneNumber,
        address = address,
        email = email,
        pendingAmount = pendingAmount,
        isSupplier = isSupplier,
        isActive = isActive,
        isSynced = isSynced,
        isDeleted = isDeleted,
        lastUpdated = lastUpdated
    )
}

fun CustomerEntity.toModel(): CustomerModel {
    return CustomerModel(
        id = id,
        businessName = businessName,
        firstName = firstName,
        lastName = lastName,
        motherLastName = motherLastName,
        documentType = documentType,
        documentNumber = documentNumber,
        phoneNumber = phoneNumber,
        address = address,
        email = email,
        pendingAmount = pendingAmount,
        isSupplier = isSupplier,
        isActive = isActive,
        isSynced = isSynced,
        isDeleted = isDeleted,
        lastUpdated = lastUpdated
    )
}