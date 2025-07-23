package com.rgk.qhatu.feature.setting.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel(
    val id: String,
    val name: String,
    val description: String? = null,
    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,
    val lastUpdated: Long = 0,
)