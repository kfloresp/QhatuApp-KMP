package com.rgk.qhatu.feature.auth.domain.usecase

import com.rgk.qhatu.feature.auth.domain.model.User
import com.rgk.qhatu.feature.auth.domain.repository.AuthRepository

class ObserveCurrentUser(private val repository: AuthRepository) {
    operator fun invoke(): User? {
        return repository.getCurrentUser
    }
}