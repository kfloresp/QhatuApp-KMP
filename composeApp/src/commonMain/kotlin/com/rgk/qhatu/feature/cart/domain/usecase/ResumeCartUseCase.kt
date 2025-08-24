package com.rgk.qhatu.feature.cart.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.cart.domain.repository.CartRepository

class ResumeCartUseCase(
    private val cartRepository: CartRepository,
) {
    suspend operator fun invoke(): SyncResult<Unit> {
        return cartRepository.resumeCart()
    }
}