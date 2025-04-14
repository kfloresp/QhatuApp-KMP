package com.rgk.qhatu.data.repository

import com.rgk.qhatu.data.remote.AuthRemoteDataSource
import com.rgk.qhatu.domain.model.UserModel
import com.rgk.qhatu.domain.repository.AuthRepository

class AuthRepositoryImpl(private val remoteDataSource: AuthRemoteDataSource) : AuthRepository {
    override suspend fun login(email: String, password: String): UserModel {
        return remoteDataSource.login(email, password)
    }

    override suspend fun register(email: String, password: String): UserModel {
        return remoteDataSource.register(email, password)
    }

    override suspend fun logout() {
        remoteDataSource.logout()
    }

    override fun getCurrentUser(): UserModel? {
        return remoteDataSource.getCurrentUser()
    }
}