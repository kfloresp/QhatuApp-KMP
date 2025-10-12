package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.repository.CategoryRepository

class UpsertCategoryUseCase(private val repository: CategoryRepository) {
    suspend operator fun invoke(data: Category): SyncResult<Unit> = safeCall {
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