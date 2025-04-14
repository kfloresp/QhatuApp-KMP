package com.rgk.qhatu.di
import com.rgk.qhatu.data.remote.AuthRemoteDataSource
import com.rgk.qhatu.data.remote.SyncRemoteDataSource
import com.rgk.qhatu.data.repository.AuthRepositoryImpl
import com.rgk.qhatu.data.repository.SyncRepositoryImpl
import com.rgk.qhatu.domain.usecase.AuthUseCase
import com.rgk.qhatu.domain.usecase.SyncUseCase
import com.rgk.qhatu.ui.feature.login.AuthViewModel
import com.rgk.qhatu.ui.feature.sync.SyncViewModel

object AppModule {
    private val authRemoteDataSource: AuthRemoteDataSource by lazy {
        AuthRemoteDataSource()
    }

    private val authRepositoryImpl: AuthRepositoryImpl by lazy {
        AuthRepositoryImpl(authRemoteDataSource)
    }

    private val authUseCase: AuthUseCase by lazy {
        AuthUseCase(authRepositoryImpl)
    }

    val authViewModel: AuthViewModel by lazy {
        AuthViewModel(authUseCase)
    }

    private val syncRemoteDataSource: SyncRemoteDataSource by lazy {
        SyncRemoteDataSource()
    }

    private val syncRepositoryImpl: SyncRepositoryImpl by lazy {
        SyncRepositoryImpl(syncRemoteDataSource)
    }

    private val syncUseCase: SyncUseCase by lazy {
        SyncUseCase(syncRepositoryImpl)
    }

    val syncViewModel: SyncViewModel by lazy {
        SyncViewModel(syncUseCase)
    }


}
