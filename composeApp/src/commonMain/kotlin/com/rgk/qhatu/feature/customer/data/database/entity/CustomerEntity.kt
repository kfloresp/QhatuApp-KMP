package com.rgk.qhatu.feature.customer.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clients")
data class CustomerEntity(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "razonSocial")
    val businessName: String? = null,

    @ColumnInfo(name = "nombre")
    val firstName: String? = null,

    @ColumnInfo(name = "apellidoPaterno")
    val lastName: String? = null,

    @ColumnInfo(name = "apellidoMaterno")
    val motherLastName: String? = null,

    @ColumnInfo(name = "tipoDocumento")
    val documentType: String,

    @ColumnInfo(name = "numeroDocumento")
    val documentNumber: String,

    @ColumnInfo(name = "celular")
    val phoneNumber: String? = null,

    @ColumnInfo(name = "direccion")
    val address: String? = null,

    @ColumnInfo(name = "correo")
    val email: String? = null,

    @ColumnInfo(name = "saldoPendiente")
    val pendingAmount: Double? = 0.0,

    @ColumnInfo(name = "flagProveedor")
    val isSupplier: Int = 0,

    @ColumnInfo(name = "flagActivo")
    val isActive: Int = 0,

    @ColumnInfo(name = "flag_sincronizado")
    val isSynced: Boolean = false,

    @ColumnInfo(name = "flag_eliminado")
    val isDeleted: Boolean = false,

    @ColumnInfo(name = "fecha_actualizacion")
    val lastUpdated: Long = 0L,
)