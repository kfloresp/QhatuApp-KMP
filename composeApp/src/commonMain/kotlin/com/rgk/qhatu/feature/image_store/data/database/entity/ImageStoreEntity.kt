package com.rgk.qhatu.feature.image_store.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_store")
data class ImageStoreEntity(
    @PrimaryKey
    val id: String,
    val entityId: String,
    val tableStore: String,
    val filename:String,
    val syncedDate: Long = 0L,
    val isSynced: Boolean = false,
    val lastUpdated: Long = 0L,
)