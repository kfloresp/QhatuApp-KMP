package com.rgk.qhatu.feature.cart.domain.usecase

import com.rgk.qhatu.feature.cart.domain.model.CartSummary
import com.rgk.qhatu.feature.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.StateFlow

class ObserveCartSummaryUseCase(
    private val cartRepository: CartRepository,
) {
    operator fun invoke(): StateFlow<CartSummary?> =
        cartRepository.observeCartSummary
}