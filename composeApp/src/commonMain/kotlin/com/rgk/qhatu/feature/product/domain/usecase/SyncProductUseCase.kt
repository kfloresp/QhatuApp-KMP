package com.rgk.qhatu.feature.product.domain.usecase

import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.domain.repository.ProductRepository

class SyncProductUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(operation: SyncOperation<Product>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.SaveLocal -> repository.saveLocal(operation.registers)
            is SyncOperation.UpsertLocal -> repository.upsertLocal(operation.register)
            is SyncOperation.LocalToRemote -> repository.syncLocalToRemote()
            is SyncOperation.RemoteToLocal -> repository.syncRemoteToLocal()
        }
    }
}
