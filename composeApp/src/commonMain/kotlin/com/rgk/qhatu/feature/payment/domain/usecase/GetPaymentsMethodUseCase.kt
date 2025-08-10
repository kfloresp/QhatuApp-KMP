package com.rgk.qhatu.feature.payment.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Configuration
import com.rgk.qhatu.feature.setting.domain.repository.ConfigurationRepository

private const val PARAM = "metodo_pago"

class GetPaymentsMethodUseCase(private val repository: ConfigurationRepository) {
    suspend operator fun invoke(): SyncResult<List<Configuration>> {
        return repository.fetchLocal(PARAM)
    }
}