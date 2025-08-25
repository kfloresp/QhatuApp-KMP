package com.rgk.qhatu.feature.operation.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operation")
data class OperationEntity(
    @PrimaryKey
    val operationId: String,
    val type: String,
    val status: String,
    val operationDate: Long,
    val lastUpdated: Long = 0L,
    val isSynced: Boolean = false,
    val syncedDate: Long = 0L,
    val isDeleted: Boolean = false,
)