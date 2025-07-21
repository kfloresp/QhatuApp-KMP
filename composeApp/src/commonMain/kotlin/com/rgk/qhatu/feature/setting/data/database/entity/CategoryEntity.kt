package com.rgk.qhatu.feature.setting.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey
    val id: String,
    val nombre: String,
    val descripcion: String?,
    val flag_sincronizado: Boolean,
    val flag_eliminado: Boolean,
    val fecha_actualizacion: Long
)
