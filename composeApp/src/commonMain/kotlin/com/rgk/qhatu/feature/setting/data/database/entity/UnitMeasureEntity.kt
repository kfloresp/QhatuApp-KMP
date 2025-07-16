package com.rgk.qhatu.feature.setting.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unit_measures")
data class UnitMeasureEntity(
    @PrimaryKey(autoGenerate = true)
    val idInternal: Int,
    val id: String,
    val nombre: String,
    val descripcion: String?,
    val abreviatura: String,
    val fecha_sincronizado: Long,
    val flag_sincronizado: Int
)
