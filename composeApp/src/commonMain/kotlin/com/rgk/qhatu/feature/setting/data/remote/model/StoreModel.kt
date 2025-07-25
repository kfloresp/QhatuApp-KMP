package com.rgk.qhatu.feature.setting.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreModel(
    @SerialName("id")
    val id: String,
    @SerialName("nombre")
    val name: String,
    @SerialName("direccion")
    val address: String? = null,
    @SerialName("celular")
    val phone: String? = null,
    @SerialName("url_logo")
    val logoUrl: String? = null,
    @SerialName("flag_sincronizado")
    val isSynced: Boolean = false,
    @SerialName("fecha_actualizacion")
    val lastUpdated: Long = 0,
)