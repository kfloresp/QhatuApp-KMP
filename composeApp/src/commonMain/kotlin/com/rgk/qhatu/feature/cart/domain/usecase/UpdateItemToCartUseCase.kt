package com.rgk.qhatu.feature.cart.domain.usecase

import com.rgk.qhatu.feature.cart.domain.model.CartItem
import com.rgk.qhatu.feature.cart.domain.repository.CartRepository

class UpdateItemToCartUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke(productId: String, quantity: Int, unitPrice: Double) {
        return cartRepository.updateCartItem(CartItem(
            productId = productId,
            quantity = quantity,
            unitPrice = unitPrice,
        ))
    }
}