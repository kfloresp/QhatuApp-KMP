package com.rgk.qhatu.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clients")
data class ClientEntity(
    @PrimaryKey(autoGenerate = true)
    val idInternal: Int,
    val id: String,
    val razonSocial: String? = null,
    val nombre: String? = null,
    val apellidoPaterno: String? = null,
    val apellidoMaterno: String? = null,
    val tipoDocumento: String,
    val numeroDocumento: String,
    val celular: String? = null,
    val direccion: String? = null,
    val correo: String? = null,
    val saldoPendiente: Double? = 0.0,
    val flagProveedor: Int = 0,
    val flagActivo: Int = 0,
    val fechaSincronizado: Long = 0,
    val flagSincronizado: Int = 0
)
