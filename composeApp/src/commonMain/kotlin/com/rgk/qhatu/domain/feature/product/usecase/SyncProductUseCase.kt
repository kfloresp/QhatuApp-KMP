package com.rgk.qhatu.domain.feature.product.usecase

import com.rgk.qhatu.domain.common.SyncOperation
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.feature.product.model.Product
import com.rgk.qhatu.domain.feature.product.repository.ProductRepository

class SyncProductUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(operation: SyncOperation<Product>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.Save -> repository.saveLocal(operation.registers)
            is SyncOperation.Update -> repository.updateLocal(operation.register)
            is SyncOperation.Upload -> repository.syncLocalToRemote()
            is SyncOperation.Download -> repository.syncRemoteToLocal()
        }
    }
}
