package com.rgk.qhatu.data.setting.remote.model

data class StoreModel(
    val id: String,
    val nombre: String,
    val direccion: String,
    val celular: String,
    val urlLogo: String,
    val flagSincronizado: Int = 0,
    val fechaSincronizado: Long = 0
)