package com.rgk.qhatu.feature.product.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.product.domain.repository.ProductRepository
import com.rgk.qhatu.shared.SharedImage

class SaveImageProductUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(image: SharedImage): SyncResult<String> {
        return productRepository.saveImageProductLocal(image)
    }
}