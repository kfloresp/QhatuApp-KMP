package com.rgk.qhatu.feature.auth.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.auth.domain.repository.AuthRepository

class LogoutUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke() {
        return repository.logout()
    }
}