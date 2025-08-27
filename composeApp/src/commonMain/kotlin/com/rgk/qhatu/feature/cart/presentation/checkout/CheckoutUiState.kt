package com.rgk.qhatu.feature.cart.presentation.checkout

import com.rgk.qhatu.feature.product.domain.model.Product

sealed class CheckoutUiState {
    data class Success(val result: List<Product>) : CheckoutUiState()
    object SuccessSave : CheckoutUiState()
    object Loading : CheckoutUiState()
    object Empty : CheckoutUiState()
    data class Error(val message: String) : CheckoutUiState()
}