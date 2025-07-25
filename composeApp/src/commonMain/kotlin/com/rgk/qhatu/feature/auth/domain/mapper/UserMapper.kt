package com.rgk.qhatu.feature.auth.domain.mapper

import com.rgk.qhatu.feature.auth.data.remote.model.UserModel
import com.rgk.qhatu.feature.auth.domain.model.User

fun UserModel.toDomain(): User = User(
    id = id,
    email = email,
    displayName = displayName
)