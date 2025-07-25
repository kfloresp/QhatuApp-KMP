package com.rgk.qhatu.feature.setting.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnitMeasureModel(
    @SerialName("id")
    val id: String,
    @SerialName("nombre")
    val name: String,
    @SerialName("descripcion")
    val description: String? = null,
    @SerialName("abreviatura")
    val abbreviation: String? = null,
    @SerialName("flag_sincronizado")
    val isSynced: Boolean = false,
    @SerialName("flag_eliminado")
    val isDeleted: Boolean = false,
    @SerialName("fecha_actualizacion")
    val lastUpdated: Long = 0,
)
