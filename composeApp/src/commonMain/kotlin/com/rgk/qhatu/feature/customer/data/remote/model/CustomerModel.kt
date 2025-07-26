package com.rgk.qhatu.feature.customer.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomerModel(
    val id: String,

    @SerialName("razon_social")
    val businessName: String? = null,

    @SerialName("nombre")
    val firstName: String? = null,

    @SerialName("apellido_paterno")
    val lastName: String? = null,

    @SerialName("apellido_materno")
    val motherLastName: String? = null,

    @SerialName("tipo_documento")
    val documentType: String,

    @SerialName("numero_documento")
    val documentNumber: String,

    @SerialName("celular")
    val phoneNumber: String? = null,

    @SerialName("direccion")
    val address: String? = null,

    @SerialName("correo")
    val email: String? = null,

    @SerialName("saldo_pendiente")
    val pendingAmount: Double? = 0.0,

    @SerialName("flag_proveedor")
    val isSupplier: Int = 0,

    @SerialName("flag_activo")
    val isActive: Int = 0,

    @SerialName("flag_sincronizado")
    val isSynced: Boolean = false,

    @SerialName("flag_eliminado")
    val isDeleted: Boolean = false,

    @SerialName("fecha_actualizacion")
    val lastUpdated: Long = 0L,
)