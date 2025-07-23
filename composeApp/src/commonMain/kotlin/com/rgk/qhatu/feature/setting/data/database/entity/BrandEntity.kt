package com.rgk.qhatu.feature.setting.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brands")
data class BrandEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "nombre")
    val name: String,

    @ColumnInfo(name = "descripcion")
    val description: String?,

    @ColumnInfo(name = "flag_sincronizado")
    val isSynced: Boolean,

    @ColumnInfo(name = "flag_eliminado")
    val isDeleted: Boolean,

    @ColumnInfo(name = "fecha_actualizacion")
    val lastUpdated: Long,
)
