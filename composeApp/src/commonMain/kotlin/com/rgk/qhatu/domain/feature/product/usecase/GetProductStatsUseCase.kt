package com.rgk.qhatu.domain.feature.product.usecase

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.feature.product.repository.ProductRepository

class GetProductStatsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return productRepository.getStats()
    }
}
