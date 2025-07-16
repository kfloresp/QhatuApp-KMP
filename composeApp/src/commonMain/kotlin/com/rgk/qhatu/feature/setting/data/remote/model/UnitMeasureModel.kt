package com.rgk.qhatu.feature.setting.data.remote.model
import kotlinx.serialization.Serializable

@Serializable
data class UnitMeasureModel(
    val id: String,
    val nombre: String,
    val descripcion: String? = null,
    val abreviatura: String,
    val fecha_sincronizado: Long = 0,
    val flag_sincronizado: Int = 0
)
