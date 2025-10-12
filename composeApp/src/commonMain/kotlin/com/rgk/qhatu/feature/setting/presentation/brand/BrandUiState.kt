package com.rgk.qhatu.feature.setting.presentation.brand

import com.rgk.qhatu.feature.setting.domain.model.Brand

sealed class BrandUiState {
    data class Success(val result: List<Brand>, val query: String = "") : BrandUiState()
    object Loading : BrandUiState()
    object Empty : BrandUiState()
    data class Error(val message: String) : BrandUiState()
}

sealed class BrandFormUiState {
    object Idle : BrandFormUiState()
    data class Upsert(
        val brand: Brand,
        val isValidForm: Boolean = false,
        val isLoading: Boolean = false,
    ) : BrandFormUiState()

    data class Error(val message: String) : BrandFormUiState()
}