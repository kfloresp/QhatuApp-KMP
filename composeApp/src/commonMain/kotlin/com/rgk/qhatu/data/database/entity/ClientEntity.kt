package com.rgk.qhatu.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clients")
data class ClientEntity(
    @PrimaryKey(autoGenerate = true)
    val idInternal: Int,
    val id: String,
    val nombre: String,
    val apellido_paterno: String,
    val apellido_materno: String,
    val tipo_documento: String?,
    val numero_documento: String?,
    val celular: String?,
    val direccion: String?,
    val correo: String?,
    val saldo_pendiente: Double,
    val flag_proveedor: Int,
    val flag_activo: Int,
    val fecha_sincronizado: Long,
    val flag_sincronizado: Int
)
