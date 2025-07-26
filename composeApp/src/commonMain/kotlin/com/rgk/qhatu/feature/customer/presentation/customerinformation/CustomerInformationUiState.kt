package com.rgk.qhatu.feature.customer.presentation.customerinformation

sealed class CustomerInformationUiState {
    data class Success(val result: List<String>, val query: String = "") : CustomerInformationUiState()
    object Loading : CustomerInformationUiState()
    object Empty : CustomerInformationUiState()
    data class Error(val message: String) : CustomerInformationUiState()
}