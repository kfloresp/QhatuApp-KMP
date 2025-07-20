package com.rgk.qhatu.feature.setting.presentation.category

import com.rgk.qhatu.feature.setting.domain.model.Category

sealed class CategoryUiState {
    data class Success(val result: List<Category>) : CategoryUiState()
    object Loading : CategoryUiState()
    data class Error(val message: String) : CategoryUiState()
}