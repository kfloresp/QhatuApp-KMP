package com.rgk.qhatu.feature.auth.domain.usecase

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.auth.domain.model.User
import com.rgk.qhatu.feature.auth.domain.repository.AuthRepository

class AuthUseCase(private val repository: AuthRepository) {
    suspend fun login(email: String, password: String): SyncResult<User> {
        return repository.login(email, password)
    }
    suspend fun register(email: String, password: String): SyncResult<User> {
        return repository.register(email, password)
    }
    suspend fun logout() {
        repository.logout()
    }
    fun currentUser(): User? {
        return repository.getCurrentUser()
    }
}