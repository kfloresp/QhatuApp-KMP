package com.rgk.qhatu.feature.setting.data.remote.model
import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel(
    val id: String,
    val nombre: String,
    val descripcion: String? = null,
    val flag_sincronizado: Boolean,
    val flag_eliminado: Boolean,
    val fecha_actualizacion: Long = 0
)