package com.rgk.qhatu.feature.cart.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.cart.domain.model.CartSummary
import com.rgk.qhatu.feature.cart.domain.usecase.AddItemToCartUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.DeleteCartUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.GetRefreshCartSummaryUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.ObserveCartItemsUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.ObserveCartSummaryUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.RemoveItemToCartUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.ResumeCartUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.UpdateItemToCartUseCase
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.domain.usecase.GetProductByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val addItemToCartUseCase: AddItemToCartUseCase,
    private val updateItemToCartUseCase: UpdateItemToCartUseCase,
    private val removeItemToCartUseCase: RemoveItemToCartUseCase,
    private val observeCartItemsUseCase: ObserveCartItemsUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val getRefreshCartSummaryUseCase: GetRefreshCartSummaryUseCase,
    private val observeCartSummaryUseCase: ObserveCartSummaryUseCase,
    private val deleteCartUseCase: DeleteCartUseCase,
    private val resumeCartUseCase: ResumeCartUseCase,
) : ViewModel() {
    private val _cartSummary = MutableStateFlow<CartSummary?>(null)
    val cartSummary: StateFlow<CartSummary?> = _cartSummary.asStateFlow()

    private val _operationCart = MutableStateFlow(true)
    val operationCart: StateFlow<Boolean> = _operationCart.asStateFlow()

    private val _uiState = MutableStateFlow<CartUiState>(CartUiState.Loading)
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    init {
        refreshCartSummary()
        observeCartItems()
    }

    private fun refreshCartSummary() {
        viewModelScope.launch {
            val result = getRefreshCartSummaryUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        CartUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<*> -> {
                    observeCartSummary()
                }
            }
        }
    }

    private fun observeCartSummary() {
        viewModelScope.launch {
            observeCartSummaryUseCase()
                .filterNotNull()
                .distinctUntilChanged()
                .collect { summary ->
                    _cartSummary.value = summary
                }
        }
    }

    private fun observeCartItems() {
        viewModelScope.launch {
            observeCartItemsUseCase()
                .filterNotNull()
                .distinctUntilChanged()
                .collect { items ->
                    val productItems: List<Product> = items.mapNotNull { cartItem ->
                        getProductById(cartItem.productId)?.copy(cartItem = cartItem)
                    }

                    if (productItems.isNotEmpty()) {
                        _uiState.update {
                            CartUiState.Success(result = productItems)
                        }
                    } else {
                        _uiState.update { CartUiState.Empty }
                    }
                }
        }
    }

    private suspend fun getProductById(productId: String): Product? {
        val result = getProductByIdUseCase(productId)
        var product: Product? = null
        if (result is SyncResult.Success<Product?>) {
            product = result.data
        }
        return product
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

    fun onDeleteAllCart() {
        _uiState.update {
            CartUiState.Loading
        }
        viewModelScope.launch {
            val result = deleteCartUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        CartUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<*> -> {
                    _operationCart.update { true }
                }
            }
        }
    }

    fun onResumeCart() {
        _uiState.update {
            CartUiState.Loading
        }
        viewModelScope.launch {
            val result = resumeCartUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        CartUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<*> -> {
                    _operationCart.update { true }
                }
            }
        }
    }
}