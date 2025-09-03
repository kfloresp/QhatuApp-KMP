package com.rgk.qhatu.feature.setting.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_unit_of_measure")
data class UnitMeasureEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val abbreviation: String?,
    val isSynced: Boolean,
    val isDeleted: Boolean,
    val lastUpdated: Long,
)
