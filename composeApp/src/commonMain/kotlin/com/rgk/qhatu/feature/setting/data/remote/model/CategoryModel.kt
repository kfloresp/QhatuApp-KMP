package com.rgk.qhatu.feature.setting.data.remote.model
import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel(
    val id: String,
    val nombre: String,
    val descripcion: String? = null,
    val flag_sincronizado: Int = 0,
    val fecha_actualizacion: Long = 0
)