package com.rgk.qhatu.feature.setting.presentation.brand

import com.rgk.qhatu.feature.setting.domain.model.Brand

sealed class BrandUiState {
    data class Success(val result: List<Brand>) : BrandUiState()
    object Loading : BrandUiState()
    data class Error(val message: String) : BrandUiState()
}