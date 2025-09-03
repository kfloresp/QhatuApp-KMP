package com.rgk.qhatu.feature.cart.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.*
import com.rgk.qhatu.feature.cart.data.database.entity.CartEntity

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cart: CartEntity)

    @Query("DELETE FROM cart WHERE id = :cartId")
    suspend fun deleteCart(cartId: String)

    @Query("SELECT * FROM cart WHERE isActive = 1 LIMIT 1")
    suspend fun getActiveCart(): CartEntity?

    @Query("SELECT * FROM cart WHERE id =:id LIMIT 1")
    suspend fun getCartById(id:String): CartEntity?

    @Query("SELECT * FROM cart")
    suspend fun getAllCarts(): List<CartEntity>

    @Query("SELECT * FROM cart where isActive = 0")
    suspend fun getAllCartsInactive(): List<CartEntity>

    @Query("UPDATE cart SET isActive = 0")
    suspend fun deactivateAllCarts()

    @Transaction
    suspend fun setActiveCart(cartId: String) {
        deactivateAllCarts()
        setCartActive(cartId)
    }

    @Query("UPDATE cart SET isActive = 1 WHERE id = :cartId")
    suspend fun setCartActive(cartId: String)

    @Query("UPDATE cart SET isActive = 0 WHERE id = :cartId")
    suspend fun setCartResume(cartId: String)

    @Query("SELECT SUM(totalPrice) FROM cart_item WHERE cartId = :cartId")
    suspend fun getCartTotal(cartId: String): Double?

    @Query("""
        SELECT SUM(totalPrice) 
        FROM cart_item 
        WHERE cartId = (SELECT id FROM cart WHERE isActive = 1 LIMIT 1)
    """)
    suspend fun getActiveCartTotal(): Double?
}
