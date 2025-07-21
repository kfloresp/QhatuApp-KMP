package com.rgk.qhatu.feature.setting.domain.model

data class Category(
    val id: String,
    val nombre: String,
    val descripcion: String? = null,
    val flag_sincronizado: Boolean,
    val flag_eliminado: Boolean,
    val fecha_actualizacion: Long = 0,
)