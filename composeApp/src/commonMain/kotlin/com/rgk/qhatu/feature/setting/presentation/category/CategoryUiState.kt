package com.rgk.qhatu.feature.setting.presentation.category

import com.rgk.qhatu.feature.setting.domain.model.Category

sealed class CategoryUiState {
    data class Success(val result: List<Category>, val query: String = "") : CategoryUiState()
    object Loading : CategoryUiState()
    object Empty : CategoryUiState()
    data class Error(val message: String) : CategoryUiState()
}

sealed class CategoryFormUiState {
    object Idle : CategoryFormUiState()
    data class Upsert(val category: Category, val isValidForm: Boolean = false) : CategoryFormUiState()
    object Loading : CategoryFormUiState()
    data class Error(val message: String) : CategoryFormUiState()
}