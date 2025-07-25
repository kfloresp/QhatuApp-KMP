package com.rgk.qhatu.feature.product.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.product.domain.repository.ProductRepository

class GetProductStatsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return productRepository.getStats()
    }
}
