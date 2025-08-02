package com.rgk.qhatu.feature.customer.presentation.customerprofile

import com.rgk.qhatu.feature.customer.domain.model.Customer

sealed class CustomerProfileUiState {
    data class Success(val result: Customer) : CustomerProfileUiState()
    object Loading : CustomerProfileUiState()
    data class Error(val message: String) : CustomerProfileUiState()
}