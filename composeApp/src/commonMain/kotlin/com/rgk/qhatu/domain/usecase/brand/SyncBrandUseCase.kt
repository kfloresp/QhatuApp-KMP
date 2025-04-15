package com.rgk.qhatu.domain.usecase.brand

import com.rgk.qhatu.domain.common.SyncOperation
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.model.AuditLog
import com.rgk.qhatu.domain.model.Brand
import com.rgk.qhatu.domain.repository.AuditLogRepository
import com.rgk.qhatu.domain.repository.BrandRepository

class SyncBrandUseCase(private val repository: BrandRepository) {
    suspend operator fun invoke(operation: SyncOperation<Brand>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.Save -> repository.saveLocal(operation.registers)
            is SyncOperation.Update -> repository.updateLocal(operation.register)
            is SyncOperation.Upload -> repository.syncLocalToRemote()
            is SyncOperation.Download -> repository.syncRemoteToLocal()
        }
    }
}
