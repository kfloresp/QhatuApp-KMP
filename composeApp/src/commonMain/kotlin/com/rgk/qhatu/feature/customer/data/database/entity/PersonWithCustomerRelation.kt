package com.rgk.qhatu.feature.customer.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PersonWithCustomerRelation(
    @Embedded val person: PersonEntity,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "customerId"
    )
    val customer: CustomerEntity,
)