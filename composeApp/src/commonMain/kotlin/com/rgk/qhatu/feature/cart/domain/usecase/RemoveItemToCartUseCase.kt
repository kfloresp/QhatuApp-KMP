package com.rgk.qhatu.feature.cart.domain.usecase

import com.rgk.qhatu.feature.cart.domain.repository.CartRepository

class RemoveItemToCartUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke(idProduct: String) {
        return cartRepository.deleteCartItem(idProduct)
    }
}