package com.rgk.qhatu.feature.cart.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.cart.domain.model.Cart
import com.rgk.qhatu.feature.cart.domain.repository.CartRepository

class GetCartsInactive(
    private val cartRepository: CartRepository,
) {
    suspend operator fun invoke(): SyncResult<List<Cart>> {
        return cartRepository.getCartsInactive()
    }
}