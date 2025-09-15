package com.rgk.qhatu.feature.customer.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "customer_company",
    foreignKeys = [
        ForeignKey(
            entity = CustomerEntity::class,
            parentColumns = ["customerId"],
            childColumns = ["customerId"],
            onDelete = ForeignKey.Companion.CASCADE
        )
    ],
    indices = [Index("customerId")]
)
data class CompanyEntity(
    @PrimaryKey
    val customerId: String,
    val companyName: String,
)