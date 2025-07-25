package com.rgk.qhatu.feature.setting.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stores")
data class StoreEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "nombre")
    val name: String,

    @ColumnInfo(name = "direccion")
    val address: String?,

    @ColumnInfo(name = "celular")
    val phone: String?,

    @ColumnInfo(name = "urlLogo")
    val logoUrl: String?,

    @ColumnInfo(name = "flag_sincronizado")
    val isSynced: Boolean,

    @ColumnInfo(name = "fecha_actualizacion")
    val lastUpdated: Long,
)