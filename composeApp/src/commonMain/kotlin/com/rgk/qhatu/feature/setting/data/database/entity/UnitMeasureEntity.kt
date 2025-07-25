package com.rgk.qhatu.feature.setting.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unit_measures")
data class UnitMeasureEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "nombre")
    val name: String,

    @ColumnInfo(name = "descripcion")
    val description: String?,

    @ColumnInfo(name = "abreviatura")
    val abbreviation: String,

    @ColumnInfo(name = "flag_sincronizado")
    val isSynced: Boolean,

    @ColumnInfo(name = "flag_eliminado")
    val isDeleted: Boolean,

    @ColumnInfo(name = "fecha_actualizacion")
    val lastUpdated: Long,
)
