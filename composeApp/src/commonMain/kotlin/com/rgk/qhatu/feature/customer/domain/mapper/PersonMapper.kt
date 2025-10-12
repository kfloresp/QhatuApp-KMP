package com.rgk.qhatu.feature.customer.domain.mapper

import com.rgk.qhatu.feature.customer.data.database.entity.PersonEntity
import com.rgk.qhatu.feature.customer.domain.model.Person

fun Person.toEntity(): PersonEntity {
    return PersonEntity(
        customerId = customerId,
        firstName = firstName,
        lastName = lastName,
        motherLastName = motherLastName
    )
}

fun PersonEntity.toDomain(): Person {
    return Person(
        customerId = customerId,
        firstName = firstName,
        lastName = lastName,
        motherLastName = motherLastName
    )
}
