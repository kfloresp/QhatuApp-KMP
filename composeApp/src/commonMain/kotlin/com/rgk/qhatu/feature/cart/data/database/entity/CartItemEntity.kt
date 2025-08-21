package com.rgk.qhatu.feature.cart.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.utils.TimeUtils

@Entity(
    tableName = "cart_item",
    foreignKeys = [
        ForeignKey(
            entity = CartEntity::class,
            parentColumns = ["id"],
            childColumns = ["cartId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("cartId")]
)
data class CartItemEntity(
    @PrimaryKey val id: String = generateUUID(),
    val cartId: String,
    val productId: String,
    val quantity: Int = 0,
    val unitPrice: Double = 0.0,
    val totalPrice: Double = 0.0,
    val lastUpdated: Long = TimeUtils.getCurrentTimestamp(),
)