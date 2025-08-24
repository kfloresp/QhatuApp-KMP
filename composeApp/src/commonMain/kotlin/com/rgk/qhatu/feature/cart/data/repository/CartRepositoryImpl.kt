package com.rgk.qhatu.feature.cart.data.repository

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.cart.data.database.dao.CartDao
import com.rgk.qhatu.feature.cart.data.database.dao.CartItemDao
import com.rgk.qhatu.feature.cart.domain.mapper.toDomain
import com.rgk.qhatu.feature.cart.domain.mapper.toEntity
import com.rgk.qhatu.feature.cart.domain.model.Cart
import com.rgk.qhatu.feature.cart.domain.model.CartItem
import com.rgk.qhatu.feature.cart.domain.model.CartSummary
import com.rgk.qhatu.feature.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartRepositoryImpl(
    private val cartDao: CartDao,
    private val cartItemDao: CartItemDao,
) : CartRepository {

    private val _observeCartSummary = MutableStateFlow<CartSummary?>(null)
    override val observeCartSummary: StateFlow<CartSummary?> get() = _observeCartSummary.asStateFlow()

    private val _observeCartItems = MutableStateFlow<List<CartItem>?>(null)
    override val observeCartItems: StateFlow<List<CartItem>?> get() = _observeCartItems.asStateFlow()

    override suspend fun resumeCart(): SyncResult<Unit> = safeCall {
        val activeCart = cartDao.getActiveCart()
        activeCart?.let {
            cartDao.setCartResume(it.id)
            refreshCartSummary()
        }
    }

    override suspend fun createCart(cart: Cart) {
        cartDao.deactivateAllCarts()
        cartDao.insertCart(
            cart.copy(
                isActive = true,
            ).toEntity()
        )
        refreshCartSummary()
    }

    override suspend fun deleteCart(): SyncResult<Unit> = safeCall {
        val activeCart = cartDao.getActiveCart()
        activeCart?.let {
            cartDao.deleteCart(it.id)
            refreshCartSummary()
        }
    }

    override suspend fun getCartById(cartId: String): Cart? {
        return cartDao.getCartById(cartId)?.toDomain()
    }

    override suspend fun getAllCarts(): List<Cart> {
        return cartDao.getAllCarts().map { it.toDomain() }
    }

    override suspend fun getActiveCart(): Cart? {
        return cartDao.getActiveCart()?.toDomain()
    }

    override suspend fun setActiveCart(cartId: String) {
        cartDao.deactivateAllCarts()
        cartDao.setActiveCart(cartId)
        refreshCartSummary()
    }

    override suspend fun addItemToCart(item: CartItem) {
        var activeCart = cartDao.getActiveCart()

        if (activeCart == null) {
            createCart(Cart(isActive = true))
            activeCart = cartDao.getActiveCart()
        }

        activeCart?.let {
            val itemExist = cartItemDao.getProductByCart(cartId = it.id, productId = item.productId)
            itemExist?.let {
                updateCartItem(item)
            } ?: run {
                val newItem = item.copy(cartId = it.id)
                cartItemDao.insertItem(newItem.toEntity())
            }
            refreshCartSummary()
        }
    }

    override suspend fun updateCartItem(item: CartItem) {
        val activeCart = cartDao.getActiveCart()
        activeCart?.let {
            cartItemDao.updateItem(
                activeCart.id,
                item.productId,
                item.quantity,
                item.unitPrice,
                item.totalPrice
            )
            refreshCartSummary()
        }
    }

    override suspend fun deleteCartItem(productId: String) {
        val activeCart = cartDao.getActiveCart()
        activeCart?.let {
            cartItemDao.deleteItem(activeCart.id, productId)
            refreshCartSummary()
        }
    }

    override suspend fun getCartItems(cartId: String): List<CartItem> {
        return cartItemDao.getItemsByCart(cartId).map { it.toDomain() }
    }

    override suspend fun refreshCartSummary(): SyncResult<Unit> = safeCall {
        val activeCart = cartDao.getActiveCart()
        var totalCart = 0.0
        var itemCount = 0
        var cartId: String? = null
        activeCart?.let { cart ->
            cartId = cart.id
            totalCart = cartDao.getCartTotal(cartId) ?: 0.0
            itemCount = cartItemDao.getItemsByCart(cartId).size
        }
        refreshCartItems(cartId)
        _observeCartSummary.value = CartSummary(total = totalCart, itemCount = itemCount)
    }

    private suspend fun refreshCartItems(cartId: String?) {
        cartId?.let {
            val items = cartItemDao.getItemsByCart(cartId).map { it.toDomain() }
            _observeCartItems.value = items
        } ?: run {
            _observeCartItems.value = emptyList()
        }
    }

}