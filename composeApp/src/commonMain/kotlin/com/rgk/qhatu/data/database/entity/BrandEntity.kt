package com.rgk.qhatu.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brands")
data class BrandEntity(
    @PrimaryKey(autoGenerate = true)
    val idInternal: Int,
    val id: String,
    val nombre: String,
    val descripcion: String?,
    val flag_sincronizado: Int,
    val fecha_actualizacion: Long
)
