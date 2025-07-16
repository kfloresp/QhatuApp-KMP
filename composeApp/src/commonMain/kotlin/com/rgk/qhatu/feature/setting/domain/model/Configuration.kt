package com.rgk.qhatu.feature.setting.domain.model

data class Configuration(
    val id : String,
    val tipo : String,
    val nombre : String,
    val descripcion : String,
    val orden : Int,
    val fecha_sincronizado : Long,
    val flag_sincronizado : Int
)