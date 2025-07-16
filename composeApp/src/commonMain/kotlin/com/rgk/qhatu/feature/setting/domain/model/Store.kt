package com.rgk.qhatu.feature.setting.domain.model

data class Store(
    val id: String,
    val nombre: String,
    val direccion: String,
    val celular: String,
    val urlLogo: String,
    val flagSincronizado: Int = 0,
    val fechaSincronizado: Long = 0
)