package com.rgk.qhatu.feature.search.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.cart.domain.model.CartSummary
import com.rgk.qhatu.feature.cart.domain.usecase.AddItemToCartUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.GetRefreshCartSummaryUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.ObserveCartItemsUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.ObserveCartSummaryUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.RemoveItemToCartUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.UpdateItemToCartUseCase
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.domain.usecase.GetAllProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val addItemToCartUseCase: AddItemToCartUseCase,
    private val updateItemToCartUseCase: UpdateItemToCartUseCase,
    private val removeItemToCartUseCase: RemoveItemToCartUseCase,
    private val observeCartSummaryUseCase: ObserveCartSummaryUseCase,
    private val observeCartItemsUseCase: ObserveCartItemsUseCase,
    private val getRefreshCartSummaryUseCase: GetRefreshCartSummaryUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase,
) : ViewModel() {

    private var allItems: List<Product> = emptyList()

    private val _cartSummary = MutableStateFlow<CartSummary?>(null)
    val cartSummary: StateFlow<CartSummary?> = _cartSummary.asStateFlow()

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        refreshCartSummary()
        fetchLocal()
    }

    private fun fetchLocal() {
        viewModelScope.launch {
            _uiState.update {
                SearchUiState.Loading
            }
            val result = getAllProductsUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        SearchUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<List<Product>> -> {
                    allItems = result.data
                    if (allItems.isNotEmpty()) {
                        _uiState.update {
                            SearchUiState.Success(
                                result = allItems
                            )
                        }
                        observeCartItems()
                    } else {
                        _uiState.update {
                            SearchUiState.Empty
                        }
                    }
                }
            }
        }
    }

    fun onQueryChanged(query: String) {
        if (_uiState.value !is SearchUiState.Success) return

        val filtered = if (query.isBlank()) allItems
        else allItems.filter { it.name.contains(query, ignoreCase = true) }

        _uiState.value = SearchUiState.Success(
            result = filtered,
            query = query
        )
    }

    private fun refreshCartSummary() {
        viewModelScope.launch {
            val result = getRefreshCartSummaryUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        SearchUiState.Error(result.exception.message.orEmpty())
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
                    val cartItemsMap = items.associateBy { it.productId }

                    allItems = allItems.map { product ->
                        val cartItem = cartItemsMap[product.id]
                        product.copy(cartItem = cartItem)
                    }
                    val currentQuery = (_uiState.value as? SearchUiState.Success)?.query.orEmpty()

                    if (currentQuery.isNotEmpty()) {
                        onQueryChanged(currentQuery)
                    } else {
                        _uiState.update {
                            if (allItems.isNotEmpty()) {
                                SearchUiState.Success(
                                    result = allItems,
                                    query = currentQuery
                                )
                            } else {
                                SearchUiState.Empty
                            }
                        }
                    }
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