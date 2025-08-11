package com.rgk.qhatu.feature.product.presentation.productoform

import com.rgk.qhatu.feature.payment.domain.model.Payment

sealed class ProductFormUiState {
    data class SuccessUpsert(val isDeleted: Boolean) : ProductFormUiState()
    data class Success(val result: Payment) : ProductFormUiState()
    object Loading : ProductFormUiState()
    data class Error(val message: String) : ProductFormUiState()
}