package com.rgk.qhatu.feature.cart.domain.usecase

import com.rgk.qhatu.feature.cart.domain.model.CartItem
import com.rgk.qhatu.feature.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.StateFlow

class ObserveCartItemsUseCase(
    private val cartRepository: CartRepository,
) {
    operator fun invoke(): StateFlow<List<CartItem>?> =
        cartRepository.observeCartItems
}