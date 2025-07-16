package com.rgk.qhatu.feature.home.presentation.home

import androidx.lifecycle.ViewModel
import com.rgk.qhatu.feature.splash.presentation.splash.SplashUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()
}