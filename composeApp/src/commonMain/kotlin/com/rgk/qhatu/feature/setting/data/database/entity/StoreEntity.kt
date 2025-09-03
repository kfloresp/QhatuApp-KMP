package com.rgk.qhatu.feature.setting.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "store")
data class StoreEntity(
    @PrimaryKey
    val id: String,
    val commercialName: String?,
    val companyName: String?,
    val ruc: String?,
    val address: String?,
    val phone: String?,
    val logoUrl: String?,
    val isSynced: Boolean,
    val lastUpdated: Long,
)