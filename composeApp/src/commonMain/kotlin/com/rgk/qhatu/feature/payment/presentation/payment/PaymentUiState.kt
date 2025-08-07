package com.rgk.qhatu.feature.payment.presentation.payment

import com.rgk.qhatu.feature.payment.domain.model.Payment

sealed class PaymentUiState {
    data class Success(val result: List<Payment>, val query: String = "") : PaymentUiState()
    object Loading : PaymentUiState()
    object Empty : PaymentUiState()
    data class Error(val message: String) : PaymentUiState()
}