package com.rgk.qhatu.domain.feature.setting.model

data class Category(
    val id: String,
    val nombre: String,
    val descripcion: String? = null,
    val flag_sincronizado: Int = 0,
    val fecha_actualizacion: Long = 0
)