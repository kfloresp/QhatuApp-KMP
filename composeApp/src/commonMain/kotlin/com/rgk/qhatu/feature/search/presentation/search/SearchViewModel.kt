package com.rgk.qhatu.feature.search.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.feature.cart.domain.usecase.AddItemToCartUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.GetCartTotalUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.ObserveCartTotalUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.RemoveItemToCartUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.UpdateItemToCartUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(
    private val addItemToCartUseCase: AddItemToCartUseCase,
    private val updateItemToCartUseCase: UpdateItemToCartUseCase,
    private val removeItemToCartUseCase: RemoveItemToCartUseCase,
    private val observeCartTotalUseCase: ObserveCartTotalUseCase,
    private val getCartTotalUseCase: GetCartTotalUseCase,
) : ViewModel() {
    private val _totalCart = MutableStateFlow(0.0)
    val totalCart: StateFlow<Double> = _totalCart.asStateFlow()

    init {
        cartSummary()
    }
    init {
        println("CartViewModel creado -> ${this.hashCode()} useCase=${observeCartTotalUseCase.hashCode()}")
    }
    fun cartSummary() {
        viewModelScope.launch {
            getCartTotalUseCase()
            observeCartTotalUseCase()
                .filterNotNull()
                .distinctUntilChanged()
                .collect { total ->
                    _totalCart.value = total
                }
        }
    }


    fun addItemToCart(productId: String, quantity: Int, unitPrice: Double) {
        viewModelScope.launch {
            addItemToCartUseCase(
                productId = productId,
                quantity = quantity,
                unitPrice = unitPrice
            )
        }
    }

    fun updateItemToCart(productId: String, quantity: Int, unitPrice: Double) {
        viewModelScope.launch {
            updateItemToCartUseCase(
                productId = productId,
                quantity = quantity,
                unitPrice = unitPrice
            )
        }
    }

    fun removeItemToCart(productId: String) {
        viewModelScope.launch {
            removeItemToCartUseCase(productId)
        }
    }
}