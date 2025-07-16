package com.rgk.qhatu.feature.auth.data.repository

import com.rgk.qhatu.feature.auth.data.remote.AuthRemoteDataSource
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.auth.domain.mapper.toDomain
import com.rgk.qhatu.feature.auth.domain.model.User
import com.rgk.qhatu.feature.auth.domain.repository.AuthRepository
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