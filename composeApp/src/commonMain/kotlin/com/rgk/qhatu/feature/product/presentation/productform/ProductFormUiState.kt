package com.rgk.qhatu.feature.product.presentation.productform

import com.rgk.qhatu.feature.product.domain.model.Product

sealed class ProductFormUiState {
    data class SuccessUpsert(val isDeleted: Boolean) : ProductFormUiState()
    data class Success(val result: Product) : ProductFormUiState()
    object Loading : ProductFormUiState()
    data class Error(val message: String) : ProductFormUiState()
}