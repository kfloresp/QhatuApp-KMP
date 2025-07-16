package com.rgk.qhatu.domain.feature.auth.model

data class User(
    val id: String,
    val email: String,
    val displayName: String? = null
)