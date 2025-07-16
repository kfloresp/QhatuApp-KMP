package com.rgk.qhatu.domain.feature.auth.mapper

import com.rgk.qhatu.data.feature.auth.remote.model.UserModel
import com.rgk.qhatu.domain.feature.auth.model.User

fun UserModel.toDomain(): User = User(
    id = id,
    email = email,
    displayName = displayName
)