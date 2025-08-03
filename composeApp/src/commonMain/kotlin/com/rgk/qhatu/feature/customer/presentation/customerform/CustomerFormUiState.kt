package com.rgk.qhatu.feature.customer.presentation.customerform

import com.rgk.qhatu.feature.customer.domain.model.Customer

sealed class CustomerFormUiState {
    data object SuccessUpsert : CustomerFormUiState()
    data class Success(val result: Customer) : CustomerFormUiState()
    object Loading : CustomerFormUiState()
    data class Error(val message: String) : CustomerFormUiState()
}