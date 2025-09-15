package com.rgk.qhatu.feature.customer.presentation.customerform

import com.rgk.qhatu.feature.customer.domain.model.CustomerWithDetails

sealed class CustomerFormUiState {
    object Idle : CustomerFormUiState()
    data class Upsert(
        val customerWithDetails: CustomerWithDetails,
        val isValidForm: Boolean = false,
        val isLoading: Boolean = false,
    ) : CustomerFormUiState()

    data class Error(val message: String) : CustomerFormUiState()
}