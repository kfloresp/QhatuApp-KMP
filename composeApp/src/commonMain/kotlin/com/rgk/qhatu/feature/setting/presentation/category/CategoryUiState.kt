package com.rgk.qhatu.feature.setting.presentation.category

import com.rgk.qhatu.feature.setting.domain.model.Category

data class CategoryUiState(
    val isSyncing: Boolean = false,
    val categories: List<Category> = emptyList(),
    val query: String = ""
)
