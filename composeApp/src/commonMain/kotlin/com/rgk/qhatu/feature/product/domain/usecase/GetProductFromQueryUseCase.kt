package com.rgk.qhatu.feature.product.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.domain.repository.ProductRepository

class GetProductFromQueryUseCase (private val productRepository: ProductRepository) {
    suspend operator fun invoke(query: String, searchType: Int): SyncResult<List<Product>> {
        return productRepository.getProductFromQuery(query, searchType)
    }
}