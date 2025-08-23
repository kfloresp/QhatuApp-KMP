package com.rgk.qhatu.feature.cart.domain.mapper

import com.rgk.qhatu.feature.cart.data.database.entity.CartItemEntity
import com.rgk.qhatu.feature.cart.domain.model.CartItem

fun CartItemEntity.toDomain(): CartItem = CartItem(
    id = id,
    cartId = cartId,
    productId = productId,
    quantity = quantity,
    unitPrice = unitPrice,
    totalPrice = totalPrice,
    lastUpdated = lastUpdated
)

fun CartItem.toEntity(): CartItemEntity = CartItemEntity(
    id = id,
    cartId = cartId,
    productId = productId,
    quantity = quantity,
    unitPrice = unitPrice,
    totalPrice = totalPrice,
    lastUpdated = lastUpdated
)
