package com.rgk.qhatu.feature.customer.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CompanyWithCustomerRelation(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "customerId"
    )
    val customer: CustomerEntity,
)