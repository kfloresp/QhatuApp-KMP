package com.rgk.qhatu.feature.customer.presentation.customersummary

sealed class CustomerSummaryUiState {
    data class Success(val result: List<String>, val query: String = "") : CustomerSummaryUiState()
    object Loading : CustomerSummaryUiState()
    object Empty : CustomerSummaryUiState()
    data class Error(val message: String) : CustomerSummaryUiState()
}