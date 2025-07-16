package com.rgk.qhatu.feature.setting.domain.usecase

import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import com.rgk.qhatu.feature.setting.domain.repository.UnitMeasureRepository

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
