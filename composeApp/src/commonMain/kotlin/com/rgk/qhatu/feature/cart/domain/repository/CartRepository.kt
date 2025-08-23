package com.rgk.qhatu.feature.cart.domain.repository

import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.cart.domain.model.Cart
import com.rgk.qhatu.feature.cart.domain.model.CartItem
import com.rgk.qhatu.feature.cart.domain.model.CartSummary
import kotlinx.coroutines.flow.StateFlow

interface CartRepository {
    suspend fun createCart(cart: Cart)
    suspend fun deleteCart(cartId: String)
    suspend fun getCartById(cartId: String): Cart?
    suspend fun getAllCarts(): List<Cart>
    suspend fun getActiveCart(): Cart?
    suspend fun setActiveCart(cartId: String)
    suspend fun addItemToCart(item: CartItem)
    suspend fun updateCartItem(item: CartItem)
    suspend fun deleteCartItem(productId: String)
    suspend fun getCartItems(cartId: String): List<CartItem>
    suspend fun refreshCartSummary(): SyncResult<Unit>
    val observeCartSummary: StateFlow<CartSummary?>
    val observeCartItems: StateFlow<List<CartItem>?>
}