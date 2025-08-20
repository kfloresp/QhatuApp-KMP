package com.rgk.qhatu.feature.product.domain.usecase

import com.rgk.qhatu.common.model.ImageOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.product.domain.model.ImageProduct
import com.rgk.qhatu.feature.product.domain.repository.ProductRepository

class SyncImageProductUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(operation: ImageOperation<ImageProduct>): SyncResult<*> {
        return when (operation) {

            is ImageOperation.DeleteLocal -> {
                repository.deleteImageProduct(operation.registers)
            }

            is ImageOperation.SaveLocal -> {
                repository.upsertImageProduct(operation.registers)
            }
        }
    }
}
