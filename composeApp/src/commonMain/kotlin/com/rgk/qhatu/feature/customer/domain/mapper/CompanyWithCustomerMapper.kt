package com.rgk.qhatu.feature.customer.domain.mapper

import com.rgk.qhatu.feature.customer.data.database.entity.CompanyWithCustomerRelation
import com.rgk.qhatu.feature.customer.domain.model.CompanyWithCustomer

fun CompanyWithCustomerRelation.toDomain(): CompanyWithCustomer {
    return CompanyWithCustomer(
        company = this.company.toDomain(),
        customer = this.customer.toDomain()
    )
}

fun CompanyWithCustomer.toEntity(): CompanyWithCustomerRelation {
    return CompanyWithCustomerRelation(
        company = this.company.toEntity(),
        customer = this.customer.toEntity(),
    )
}