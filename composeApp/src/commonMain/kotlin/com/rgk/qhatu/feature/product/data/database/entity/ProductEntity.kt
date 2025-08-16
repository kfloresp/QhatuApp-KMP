package com.rgk.qhatu.feature.product.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: String,
    val ean: String,
    val name: String,
    val categoryId: String?,
    val storageTypeId: String?,
    val brandId: String?,
    val unitPrice: Double,
    val unitMeasureId: String,
    val isBatch: Boolean = false,
    val isActive: Boolean = false,
    val syncedDate: Long = 0L,
    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,
    val lastUpdated: Long = 0L,
)