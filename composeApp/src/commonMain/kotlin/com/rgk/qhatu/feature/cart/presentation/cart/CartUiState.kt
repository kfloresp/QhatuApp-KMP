package com.rgk.qhatu.feature.cart.presentation.cart

import com.rgk.qhatu.feature.product.domain.model.Product

sealed class CartUiState {
    data class Success(val result: List<Product>) : CartUiState()
    object Loading : CartUiState()
    object Empty : CartUiState()
    data class Error(val message: String) : CartUiState()
}