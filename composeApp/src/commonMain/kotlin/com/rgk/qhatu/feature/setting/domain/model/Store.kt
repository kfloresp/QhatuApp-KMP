package com.rgk.qhatu.feature.setting.domain.model

import com.rgk.qhatu.common.util.generateUUID

data class Store(
    val id: String = generateUUID(),
    val commercialName: String? = null,
    val companyName: String? = null,
    val ruc: String? = null,
    val address: String? = null,
    val phone: String? = null,
    val logoUrl: String? = null,
    val isSynced: Boolean = false,
    val lastUpdated: Long = 0,
)