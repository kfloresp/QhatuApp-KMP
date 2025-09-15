package com.rgk.qhatu.feature.customer.domain.mapper

import com.rgk.qhatu.feature.customer.data.database.entity.CompanyEntity
import com.rgk.qhatu.feature.customer.domain.model.Company

fun Company.toEntity(): CompanyEntity {
    return CompanyEntity(
        customerId = customerId,
        companyName = companyName
    )
}

fun CompanyEntity.toDomain(): Company {
    return Company(
        customerId = customerId,
        companyName = companyName
    )
}