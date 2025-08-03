package com.rgk.qhatu.feature.customer.presentation.customer

import com.rgk.qhatu.feature.customer.domain.model.Customer

sealed class CustomerUiState {
    data class Success(val result: List<Customer>, val query: String = "") : CustomerUiState()
    object Loading : CustomerUiState()
    object Empty : CustomerUiState()
    data class Error(val message: String) : CustomerUiState()
}