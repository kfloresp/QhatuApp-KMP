package com.rgk.qhatu.feature.home.presentation.home

sealed class HomeUiState {
    data object Success : HomeUiState()
    data object Loading : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}