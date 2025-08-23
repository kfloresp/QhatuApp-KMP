package com.rgk.qhatu.feature.cart.domain.usecase

import com.rgk.qhatu.feature.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.StateFlow

class GetCartTotalUseCase(
    private val cartRepository: CartRepository,
) {
    suspend operator fun invoke(): Double {
        return cartRepository.cartTotalFlow()
    }
}