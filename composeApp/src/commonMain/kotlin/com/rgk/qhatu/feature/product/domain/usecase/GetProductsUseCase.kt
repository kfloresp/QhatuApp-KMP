package com.rgk.qhatu.feature.product.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.domain.repository.ProductRepository

class GetProductsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(productId: String? = null): SyncResult<List<Product>> {
        return productRepository.fetchLocal(productId)
    }
}