package com.rgk.qhatu.feature.setting.domain.model

data class Brand(
    val id: String = "",
    val name: String = "",
    val description: String? = null,
    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,
    val lastUpdated: Long = 0,
)
