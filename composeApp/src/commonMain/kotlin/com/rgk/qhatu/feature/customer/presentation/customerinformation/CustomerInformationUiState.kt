package com.rgk.qhatu.feature.customer.presentation.customerinformation

import com.rgk.qhatu.feature.customer.domain.model.Customer

sealed class CustomerInformationUiState {
    data class Success(val result: Customer) : CustomerInformationUiState()
    object Loading : CustomerInformationUiState()
    data class Error(val message: String) : CustomerInformationUiState()
}