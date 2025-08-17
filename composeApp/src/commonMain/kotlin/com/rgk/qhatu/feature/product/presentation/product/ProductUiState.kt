package com.rgk.qhatu.feature.product.presentation.product

import com.rgk.qhatu.feature.product.domain.model.Product

sealed class ProductUiState {
    data class Success(val result: List<Product>, val query: String = "") : ProductUiState()
    object Loading : ProductUiState()
    object Empty : ProductUiState()
    data class Error(val message: String) : ProductUiState()
}