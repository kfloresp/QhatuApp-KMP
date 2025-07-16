package com.rgk.qhatu.domain.feature.product.usecase

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.feature.product.model.Product
import com.rgk.qhatu.domain.feature.product.repository.ProductRepository

class GetProductFromQueryUseCase (private val productRepository: ProductRepository) {
    suspend operator fun invoke(query: String, searchType: Int): SyncResult<List<Product>> {
        return productRepository.getProductFromQuery(query, searchType)
    }
}