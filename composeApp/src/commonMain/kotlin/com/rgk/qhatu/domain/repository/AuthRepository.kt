package com.rgk.qhatu.domain.repository

import com.rgk.qhatu.domain.model.UserModel

interface AuthRepository {
    suspend fun login(email: String, password: String): UserModel
    suspend fun register(email: String, password: String): UserModel
    suspend fun logout()
    fun getCurrentUser(): UserModel?
}