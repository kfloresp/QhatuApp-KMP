package com.rgk.qhatu.feature.setting.domain.model

import com.rgk.qhatu.feature.image_store.domain.model.ImageStore

data class Store(
    val id: String = "",
    val commercialName: String? = null,
    val companyName: String? = null,
    val ruc: String? = null,
    val address: String? = null,
    val phone: String? = null,
    val images: List<ImageStore> = emptyList(),
    val isSynced: Boolean = false,
    val lastUpdated: Long = 0,
)