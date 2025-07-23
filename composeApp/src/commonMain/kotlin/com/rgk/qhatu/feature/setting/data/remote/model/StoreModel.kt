package com.rgk.qhatu.feature.setting.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class StoreModel(
    val id: String,
    val name: String,
    val address: String? = null,
    val phone: String? = null,
    val logoUrl: String? = null,
    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,
    val lastUpdated: Long = 0,
)