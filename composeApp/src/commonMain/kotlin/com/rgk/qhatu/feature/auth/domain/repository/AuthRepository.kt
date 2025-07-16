package com.rgk.qhatu.feature.auth.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.auth.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): SyncResult<User>
    suspend fun register(email: String, password: String): SyncResult<User>
    suspend fun logout()
    fun getCurrentUser(): User?
}