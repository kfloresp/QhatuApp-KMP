package com.rgk.qhatu.feature.image_store.domain.usecase

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.image_store.domain.repository.ImageStoreRepository
import com.rgk.qhatu.feature.product.domain.repository.ProductRepository
import com.rgk.qhatu.shared.SharedImage

class DeleteImageStoreUseCase(private val repository: ImageStoreRepository) {
    suspend operator fun invoke(path: String): SyncResult<Unit> = safeCall {
        repository.deleteFileImageLocal(path)
    }
}