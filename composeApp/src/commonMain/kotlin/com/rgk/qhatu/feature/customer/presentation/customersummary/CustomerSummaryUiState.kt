package com.rgk.qhatu.feature.customer.presentation.customersummary

sealed class CustomerSummaryUiState {
    data class Success(val result: List<String>) : CustomerSummaryUiState()
    object Loading : CustomerSummaryUiState()
    data class Error(val message: String) : CustomerSummaryUiState()
}