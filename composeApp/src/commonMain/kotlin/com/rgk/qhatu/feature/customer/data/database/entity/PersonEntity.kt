package com.rgk.qhatu.feature.customer.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "customer_person",
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
data class PersonEntity(
    @PrimaryKey
    val customerId: String,
    val firstName: String,
    val lastName: String,
    val motherLastName: String,
)