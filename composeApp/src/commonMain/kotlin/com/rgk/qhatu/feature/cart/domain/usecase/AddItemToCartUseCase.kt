package com.rgk.qhatu.feature.cart.domain.usecase

import com.rgk.qhatu.feature.cart.domain.model.CartItem
import com.rgk.qhatu.feature.cart.domain.repository.CartRepository

class AddItemToCartUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke(productId: String, quantity: Int, unitPrice: Double) {
        return cartRepository.addItemToCart(CartItem(
            productId = productId,
            quantity = quantity,
            unitPrice = unitPrice,
        ))
    }
}