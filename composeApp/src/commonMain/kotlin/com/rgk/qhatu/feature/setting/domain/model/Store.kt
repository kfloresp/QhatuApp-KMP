package com.rgk.qhatu.feature.setting.domain.model

data class Store(
    val id: String = "",
    val name: String = "",
    val address: String? = null,
    val phone: String? = null,
    val logoUrl: String? = null,
    val isSynced: Boolean = false,
    val lastUpdated: Long = 0,
)