package com.rgk.qhatu.feature.product.domain.usecase

import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.product.domain.model.ImageProduct
import com.rgk.qhatu.feature.product.domain.repository.ProductRepository

class SyncImageProductUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(operation: SyncOperation<ImageProduct>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.SaveLocal -> repository.upsertImageProduct(operation.registers)
            is SyncOperation.UpsertLocal -> {
                TODO()
            }
            is SyncOperation.LocalToRemote -> {
                TODO()
            }
            is SyncOperation.RemoteToLocal -> {
                TODO()
            }
        }
    }
}
