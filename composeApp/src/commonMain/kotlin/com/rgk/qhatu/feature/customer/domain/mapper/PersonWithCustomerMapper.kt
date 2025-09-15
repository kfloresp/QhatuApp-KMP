package com.rgk.qhatu.feature.customer.domain.mapper

import com.rgk.qhatu.feature.customer.data.database.entity.PersonWithCustomerRelation
import com.rgk.qhatu.feature.customer.domain.model.PersonWithCustomer

fun PersonWithCustomerRelation.toDomain(): PersonWithCustomer {
    return PersonWithCustomer(
        person = this.person.toDomain(),
        customer = this.customer.toDomain()
    )
}

fun PersonWithCustomer.toEntity(): PersonWithCustomerRelation {
    return PersonWithCustomerRelation(
        person = this.person.toEntity(),
        customer = this.customer.toEntity(),
    )
}