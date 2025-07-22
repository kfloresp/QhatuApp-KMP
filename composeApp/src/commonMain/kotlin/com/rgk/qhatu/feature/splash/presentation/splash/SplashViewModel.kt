package com.rgk.qhatu.feature.splash.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.feature.auth.domain.usecase.AuthUseCase
import com.rgk.qhatu.feature.auth.domain.usecase.ObserveCurrentUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(private val observeCurrentUser: ObserveCurrentUser) : ViewModel() {
    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        validateSession()
    }

    private fun validateSession() {
        _uiState.value = SplashUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            try {
                if (observeCurrentUser() != null) {
                    _uiState.value = SplashUiState.NavigateToHome
                } else {
                    _uiState.value = SplashUiState.NavigateToLogin
                }
            } catch (e: Exception) {
                _uiState.value = SplashUiState.Error(e)
            }
        }
    }

}