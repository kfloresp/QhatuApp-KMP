package com.rgk.qhatu.feature.cart.data.database.dao

import androidx.room.*
import com.rgk.qhatu.feature.cart.data.database.entity.CartItemEntity
import com.rgk.qhatu.feature.cart.data.database.entity.CartItemWithDetail

@Dao
interface CartItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: CartItemEntity)

    @Query("UPDATE cart_item SET quantity=:quantity,unitPrice=:unitPrice,totalPrice=:totalPrice  WHERE cartId = :cartId and productId = :productId")
    suspend fun updateItem( cartId: String,
                            productId: String,
                            quantity: Int,
                            unitPrice: Double,
                            totalPrice: Double)

    @Query("DELETE FROM cart_item WHERE cartId = :cartId and productId = :productId")
    suspend fun deleteItem(cartId: String, productId: String)

    @Query("SELECT * FROM cart_item WHERE cartId = :cartId")
    suspend fun getItemsByCart(cartId: String): List<CartItemEntity>

    @Query("""
        SELECT 
            ci.id,
            p.ean,
            p.name,
            p.categoryId,
            c.nombre AS category,
            p.storageTypeId,
            st.nombre AS storageType,
            p.brandId,
            b.nombre AS brand,
            p.unitPrice AS unitPrice,
            p.unitMeasureId,
            um.nombre AS unitMeasure,
            p.isBatch,
            p.isActive,
            ci.cartId AS idCart,
            ci.quantity AS quantityCart,
            ci.unitPrice AS unitPriceCart,
            ci.totalPrice AS totalPriceCart
        FROM cart_item ci
        INNER JOIN products p ON ci.productId = p.id
        LEFT JOIN categories c ON p.categoryId = c.id
        LEFT JOIN configurations st ON p.storageTypeId = st.id
        LEFT JOIN brands b ON p.brandId = b.id
        LEFT JOIN unit_measures um ON p.unitMeasureId = um.id
        WHERE ci.cartId = :cartId
    """)
    suspend fun getItemsWithDetail(cartId: String): List<CartItemWithDetail>
}
