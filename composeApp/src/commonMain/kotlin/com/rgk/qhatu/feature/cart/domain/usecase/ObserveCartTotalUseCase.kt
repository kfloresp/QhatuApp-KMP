package com.rgk.qhatu.feature.cart.domain.usecase

import com.rgk.qhatu.feature.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.StateFlow

class ObserveCartTotalUseCase(
    private val cartRepository: CartRepository,
) {
    operator fun invoke(): StateFlow<Double?> =
        cartRepository.observeCartTotalFlow
}