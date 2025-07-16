package com.rgk.qhatu.data.feature.setting.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "configurations")
data class ConfigurationEntity(
    @PrimaryKey(autoGenerate = true)
    val idInternal: Int,
    val id : String,
    val tipo : String,
    val nombre : String,
    val descripcion : String,
    val orden : Int,
    val fecha_sincronizado : Long,
    val flag_sincronizado : Int
)