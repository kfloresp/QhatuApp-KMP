package com.rgk.qhatu.data.feature.auth.repository

import com.rgk.qhatu.data.feature.auth.remote.AuthRemoteDataSource
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.feature.auth.mapper.toDomain
import com.rgk.qhatu.domain.feature.auth.model.User
import com.rgk.qhatu.domain.feature.auth.repository.AuthRepository
import com.rgk.qhatu.utils.FirebaseAuthException

class AuthRepositoryImpl(private val remoteDataSource: AuthRemoteDataSource) : AuthRepository {
    override suspend fun login(email: String, password: String): SyncResult<User> {
        return try {
            val user = remoteDataSource.login(email, password)
            SyncResult.Success(user.toDomain())
        } catch (e: Exception) {
            SyncResult.Error(FirebaseAuthException.handleException(e))
        }
    }

    override suspend fun register(email: String, password: String): SyncResult<User> {
        return try {
            val user = remoteDataSource.register(email, password)
            SyncResult.Success(user.toDomain())
        } catch (e: Exception) {
            SyncResult.Error(FirebaseAuthException.handleException(e))
        }
    }

    override suspend fun logout() {
        remoteDataSource.logout()
    }

    override fun getCurrentUser(): User? {
        return remoteDataSource.getCurrentUser()?.toDomain()
    }
}