package com.rgk.qhatu.feature.cart.domain.mapper

import com.rgk.qhatu.feature.cart.data.database.entity.CartEntity
import com.rgk.qhatu.feature.cart.domain.model.Cart

fun CartEntity.toDomain(): Cart = Cart(
    id = id,
    createdAt = createdAt,
    isActive = isActive
)

fun Cart.toEntity(): CartEntity = CartEntity(
    id = id,
    createdAt = createdAt,
    isActive = isActive
)
