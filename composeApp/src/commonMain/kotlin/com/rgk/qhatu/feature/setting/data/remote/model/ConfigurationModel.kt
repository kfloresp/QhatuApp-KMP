package com.rgk.qhatu.feature.setting.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationModel(
    val id: String,
    val type: String,
    val name: String,
    val description: String? = null,
    val order: Int = 0,
    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,
    val lastUpdated: Long = 0,
)
