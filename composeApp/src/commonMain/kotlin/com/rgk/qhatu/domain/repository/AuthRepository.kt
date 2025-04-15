package com.rgk.qhatu.domain.repository

import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): SyncResult<User>
    suspend fun register(email: String, password: String): SyncResult<User>
    suspend fun logout()
    fun getCurrentUser(): User?
}