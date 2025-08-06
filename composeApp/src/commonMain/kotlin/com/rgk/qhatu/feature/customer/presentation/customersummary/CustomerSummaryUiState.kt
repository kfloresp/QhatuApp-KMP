package com.rgk.qhatu.feature.customer.presentation.customersummary

import com.rgk.qhatu.feature.customer.domain.model.CustomerSummary

sealed class CustomerSummaryUiState {
    data class Success(val result: List<CustomerSummary>) : CustomerSummaryUiState()
    object Loading : CustomerSummaryUiState()
    data class Error(val message: String) : CustomerSummaryUiState()
}