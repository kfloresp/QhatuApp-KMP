package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.setting.domain.model.Brand
import com.rgk.qhatu.feature.setting.domain.repository.BrandRepository
import kotlin.text.ifEmpty

class UpsertBrandUseCase(private val repository: BrandRepository) {
    suspend operator fun invoke(data: Brand): SyncResult<Unit> = safeCall {
        val storeId = data.id.ifEmpty { generateUUID() }
        val type = if (data.id.isEmpty()) {
            TypeUpsert.NEW
        } else {
            TypeUpsert.UPDATE
        }
        val updatedStore = data.copy(id = storeId)

        repository.upsertLocal(updatedStore, type)
    }
}