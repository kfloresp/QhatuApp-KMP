package com.rgk.qhatu.feature.payment.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payments")
data class PaymentEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "clientId")
    val clientId: String,

    @ColumnInfo(name = "paymentDate")
    val paymentDate: Long,

    @ColumnInfo(name = "amountPaid")
    val amountPaid: Double,

    @ColumnInfo(name = "paymentMethodId")
    val paymentMethodId: String,

    @ColumnInfo(name = "isSynced")
    val isSynced: Boolean = false,

    @ColumnInfo(name = "isDeleted")
    val isDeleted: Boolean = false,

    @ColumnInfo(name = "lastUpdated")
    val lastUpdated: Long = 0L,
)
