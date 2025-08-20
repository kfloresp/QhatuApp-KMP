package com.rgk.qhatu.feature.product.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images_products")
data class ImageProductEntity(
    @PrimaryKey
    val id: String,
    val productId: String,
    val filename:String,
    val syncedDate: Long = 0L,
    val isSynced: Boolean = false,
    val lastUpdated: Long = 0L,
)