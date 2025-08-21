package com.rgk.qhatu.feature.cart.data.repository

import com.rgk.qhatu.feature.cart.data.database.dao.CartDao
import com.rgk.qhatu.feature.cart.data.database.dao.CartItemDao
import com.rgk.qhatu.feature.cart.domain.mapper.toDomain
import com.rgk.qhatu.feature.cart.domain.mapper.toEntity
import com.rgk.qhatu.feature.cart.domain.model.Cart
import com.rgk.qhatu.feature.cart.domain.model.CartItem
import com.rgk.qhatu.feature.cart.domain.model.CartItemDetail
import com.rgk.qhatu.feature.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartRepositoryImpl(
    private val cartDao: CartDao,
    private val cartItemDao: CartItemDao,
) : CartRepository {

    private val _observeCartTotalFlow = MutableStateFlow<Double?>(null)
    override val observeCartTotalFlow: StateFlow<Double?> get() = _observeCartTotalFlow.asStateFlow()
    override suspend fun createCart(cart: Cart) {
        cartDao.deactivateAllCarts()
        cartDao.insertCart(
            cart.copy(
                isActive = true,
            ).toEntity()
        )
        cartTotalFlow()
    }

    override suspend fun deleteCart(cartId: String) {
        cartDao.deleteCart(cartId)
        cartTotalFlow()
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
    }

    override suspend fun addItemToCart(item: CartItem) {
        var activeCart = cartDao.getActiveCart()

        if (activeCart == null) {
            createCart(Cart(isActive = true))
            activeCart = cartDao.getActiveCart()
        }

        activeCart?.let {
            val newItem = item.copy(cartId = it.id)
            cartItemDao.insertItem(newItem.toEntity())
            cartTotalFlow()
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
            cartTotalFlow()
        }
    }

    override suspend fun deleteCartItem(productId: String) {
        val activeCart = cartDao.getActiveCart()
        activeCart?.let {
            cartItemDao.deleteItem(activeCart.id, productId)
            cartTotalFlow()
        }
    }

    override suspend fun getCartItems(cartId: String): List<CartItem> {
        return cartItemDao.getItemsByCart(cartId).map { it.toDomain() }
    }
    override suspend fun getCartItemsWithDetail(cartId: String): List<CartItemDetail> {
        return cartItemDao.getItemsWithDetail(cartId).map { it.toDomain() }
    }


    override suspend fun cartTotalFlow(): Double {
        val total = cartDao.getActiveCartTotal() ?: 0.0
        _observeCartTotalFlow.value = total
        return total
    }

}