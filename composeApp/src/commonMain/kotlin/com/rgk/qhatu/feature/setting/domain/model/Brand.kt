package com.rgk.qhatu.feature.setting.domain.model

data class Brand(
    val id: String,
    val nombre: String,
    val descripcion: String? = null,
    val flag_sincronizado: Int = 0,
    val fecha_actualizacion: Long = 0
)
