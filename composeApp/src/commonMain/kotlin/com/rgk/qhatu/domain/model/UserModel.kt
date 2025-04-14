package com.rgk.qhatu.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: String,
    val email: String,
    val displayName: String? = null
)