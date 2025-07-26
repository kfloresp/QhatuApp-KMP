package com.rgk.qhatu.feature.customer.presentation.customer

sealed class CustomerUiState {
    data class Success(val result: List<String>, val query: String = "") : CustomerUiState()
    object Loading : CustomerUiState()
    object Empty : CustomerUiState()
    data class Error(val message: String) : CustomerUiState()
}