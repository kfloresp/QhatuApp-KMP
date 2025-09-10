package com.rgk.qhatu.feature.setting.domain.model

data class Category(
    val id: String = "",
    val name: String = "",
    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,
    val lastUpdated: Long = 0,
)