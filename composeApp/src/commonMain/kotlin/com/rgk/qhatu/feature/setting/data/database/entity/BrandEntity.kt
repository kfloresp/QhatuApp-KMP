package com.rgk.qhatu.feature.setting.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_brand")
data class BrandEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val isSynced: Boolean,
    val isDeleted: Boolean,
    val lastUpdated: Long,
)