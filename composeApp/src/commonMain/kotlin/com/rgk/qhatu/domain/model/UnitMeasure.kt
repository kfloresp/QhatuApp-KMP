package com.rgk.qhatu.domain.model

data class UnitMeasure(
    val id: String,
    val nombre: String,
    val descripcion: String? = null,
    val abreviatura: String,
    val fecha_sincronizado: Long = 0,
    val flag_sincronizado: Int = 0
)
