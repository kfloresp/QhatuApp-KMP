package com.rgk.qhatu.presentation.feature.home

sealed class HomeUiState {
    data object Success : HomeUiState()
    data object Loading : HomeUiState()
    data class Error(val message: Throwable) : HomeUiState()
}