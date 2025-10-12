package com.rgk.qhatu.feature.sale.presentation.sale

import com.rgk.qhatu.feature.product.domain.model.Product

sealed class SaleUiState {
    data class Success(val result: List<Product>, val query: String = "") : SaleUiState()
    object Loading : SaleUiState()
    object Empty : SaleUiState()
    data class Error(val message: String) : SaleUiState()
}