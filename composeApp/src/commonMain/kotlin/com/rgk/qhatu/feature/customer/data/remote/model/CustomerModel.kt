package com.rgk.qhatu.feature.customer.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomerModel(
    val id: String,

    @SerialName("razonSocial")
    val businessName: String? = null,

    @SerialName("nombre")
    val firstName: String? = null,

    @SerialName("apellidoPaterno")
    val lastName: String? = null,

    @SerialName("apellidoMaterno")
    val motherLastName: String? = null,

    @SerialName("tipoDocumento")
    val documentType: String,

    @SerialName("numeroDocumento")
    val documentNumber: String,

    @SerialName("celular")
    val phoneNumber: String? = null,

    @SerialName("direccion")
    val address: String? = null,

    @SerialName("correo")
    val email: String? = null,

    @SerialName("saldoPendiente")
    val pendingAmount: Double? = 0.0,

    @SerialName("flagProveedor")
    val isSupplier: Int = 0,

    @SerialName("flagActivo")
    val isActive: Int = 0,

    @SerialName("flag_sincronizado")
    val isSynced: Boolean = false,

    @SerialName("flag_eliminado")
    val isDeleted: Boolean = false,

    @SerialName("fecha_actualizacion")
    val lastUpdated: Long = 0L,
)