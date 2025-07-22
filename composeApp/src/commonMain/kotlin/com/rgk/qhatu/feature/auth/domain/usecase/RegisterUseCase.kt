package com.rgk.qhatu.feature.auth.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.auth.domain.model.User
import com.rgk.qhatu.feature.auth.domain.repository.AuthRepository

class RegisterUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): SyncResult<User> {
        return repository.register(email, password)
    }
}