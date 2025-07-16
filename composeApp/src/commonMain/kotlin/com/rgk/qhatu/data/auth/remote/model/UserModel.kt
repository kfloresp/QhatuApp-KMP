package com.rgk.qhatu.data.auth.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: String,
    val email: String,
    val displayName: String? = null
)