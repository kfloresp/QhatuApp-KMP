package com.rgk.qhatu.ui.feature.splash

sealed class SplashUiState {
    data object NavigateToHome : SplashUiState()
    data object NavigateToLogin : SplashUiState()
    data object Loading : SplashUiState()
    data class Error(val message: Throwable) : SplashUiState()
}