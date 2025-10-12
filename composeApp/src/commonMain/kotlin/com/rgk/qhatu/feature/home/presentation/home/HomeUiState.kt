package com.rgk.qhatu.feature.home.presentation.home

import com.rgk.qhatu.feature.cart.domain.model.Cart

data class HomeScreenUiState(
    val homeUiState: HomeUiState = HomeUiState.Loading,
    val cartUiState: CartUiState = CartUiState.Loading,
)

sealed class HomeUiState {
    data object Success : HomeUiState()
    data object Loading : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}

sealed class CartUiState {
    data class Success(val carts: List<Cart>) : CartUiState()
    data object Loading : CartUiState()
    data class Error(val message: String) : CartUiState()
}