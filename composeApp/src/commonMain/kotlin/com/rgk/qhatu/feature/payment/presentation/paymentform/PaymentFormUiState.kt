package com.rgk.qhatu.feature.payment.presentation.paymentform

import com.rgk.qhatu.feature.payment.domain.model.Payment

sealed class PaymentFormUiState {
    data class SuccessUpsert(val isDeleted: Boolean) : PaymentFormUiState()
    data class Success(val result: Payment) : PaymentFormUiState()
    object Loading : PaymentFormUiState()
    data class Error(val message: String) : PaymentFormUiState()
}