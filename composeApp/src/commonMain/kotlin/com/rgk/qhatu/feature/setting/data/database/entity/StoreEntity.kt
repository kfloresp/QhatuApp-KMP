package com.rgk.qhatu.feature.setting.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stores")
data class StoreEntity(
    @PrimaryKey(autoGenerate = true)
    val idInternal: Int = 0,
    val id: String,
    val nombre: String,
    val direccion: String,
    val celular: String,
    val urlLogo: String,
    val flagSincronizado: Int,
    val fechaSincronizado: Long
)