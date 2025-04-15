package com.rgk.qhatu.domain.usecase.unitMeasure

import com.rgk.qhatu.domain.common.SyncOperation
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.model.UnitMeasure
import com.rgk.qhatu.domain.repository.UnitMeasureRepository

class SyncUnitMeasureUseCase(private val repository: UnitMeasureRepository) {
    suspend operator fun invoke(operation: SyncOperation<UnitMeasure>): SyncResult<*> {
        return when (operation) {
            is SyncOperation.Save -> repository.saveLocal(operation.registers)
            is SyncOperation.Update -> repository.updateLocal(operation.register)
            is SyncOperation.Upload -> repository.syncLocalToRemote()
            is SyncOperation.Download -> repository.syncRemoteToLocal()
        }
    }
}
