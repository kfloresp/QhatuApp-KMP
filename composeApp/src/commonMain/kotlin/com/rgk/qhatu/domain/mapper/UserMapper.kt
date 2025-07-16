package com.rgk.qhatu.domain.mapper

import com.rgk.qhatu.data.auth.remote.model.UserModel
import com.rgk.qhatu.domain.model.User

fun UserModel.toDomain(): User = User(
    id = id,
    email = email,
    displayName = displayName
)