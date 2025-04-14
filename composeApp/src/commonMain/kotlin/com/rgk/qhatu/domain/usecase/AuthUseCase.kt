package com.rgk.qhatu.domain.usecase

import com.rgk.qhatu.domain.model.UserModel
import com.rgk.qhatu.domain.repository.AuthRepository

class AuthUseCase(private val repository: AuthRepository) {
    suspend fun login(email: String, password: String): UserModel {
        return repository.login(email, password)
    }
    suspend fun register(email: String, password: String): UserModel {
        return repository.register(email, password)
    }
    suspend fun logout() {
        repository.logout()
    }
    fun currentUser(): UserModel? {
        return repository.getCurrentUser()
    }
}