package com.rgk.qhatu.domain.usecase.product

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.model.Product
import com.rgk.qhatu.domain.repository.ProductRepository

class GetProductFromQueryUseCase (private val productRepository: ProductRepository) {
    suspend operator fun invoke(query: String, searchType: Int): SyncResult<List<Product>> {
        return productRepository.getProductFromQuery(query, searchType)
    }
}