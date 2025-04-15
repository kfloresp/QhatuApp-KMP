package com.rgk.qhatu.domain.usecase.product

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.repository.ProductRepository

class GetProductStatsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(): SyncResult<SyncStats> {
        return productRepository.getStats()
    }
}
