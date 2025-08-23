package com.rgk.qhatu.feature.cart.data.database.dao

import androidx.room.*
import com.rgk.qhatu.feature.cart.data.database.entity.CartItemEntity

@Dao
interface CartItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: CartItemEntity)

    @Query(
        """UPDATE cart_item SET quantity=:quantity,
        unitPrice=:unitPrice,totalPrice=:totalPrice  
        WHERE cartId = :cartId and productId = :productId"""
    )
    suspend fun updateItem(
        cartId: String,
        productId: String,
        quantity: Int,
        unitPrice: Double,
        totalPrice: Double,
    )

    @Query("DELETE FROM cart_item WHERE cartId = :cartId and productId = :productId")
    suspend fun deleteItem(cartId: String, productId: String)

    @Query("SELECT * FROM cart_item WHERE cartId = :cartId")
    suspend fun getItemsByCart(cartId: String): List<CartItemEntity>

    @Query("SELECT * FROM cart_item WHERE cartId = :cartId and productId = :productId")
    suspend fun getProductByCart(cartId: String, productId: String): CartItemEntity?

    @Query("SELECT quantity FROM cart_item WHERE cartId = :cartId and productId = :productId")
    suspend fun getItemQuantity(cartId: String, productId: String): Int?
}
