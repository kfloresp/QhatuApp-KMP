package com.rgk.qhatu.feature.cart.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey val id: String,
    val createdAt: Long,
    val isActive: Boolean = true
)
