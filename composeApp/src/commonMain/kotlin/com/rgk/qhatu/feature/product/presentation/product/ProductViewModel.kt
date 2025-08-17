package com.rgk.qhatu.feature.product.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.domain.usecase.GetProductsUseCase
import com.rgk.qhatu.feature.product.domain.usecase.SyncProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val syncProductUseCase: SyncProductUseCase,
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {
    private var allItems: List<Product> = emptyList()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()
    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    init {
        onPullRefresh()
    }

    fun onPullRefresh() {
        _isRefreshing.update { true }
        viewModelScope.launch {
            fetchLocal()
            _isRefreshing.update { false }
        }
    }

    private suspend fun fetchLocal() {
        _uiState.update {
            ProductUiState.Loading
        }
        val result = getProductsUseCase()
        when (result) {
            is SyncResult.Error -> {
                _uiState.update {
                    ProductUiState.Error(result.exception.message.orEmpty())
                }
            }

            is SyncResult.Success<List<Product>> -> {
                allItems = result.data
                if (allItems.isNotEmpty()) {
                    _uiState.update {
                        ProductUiState.Success(
                            result = allItems
                        )
                    }
                } else {
                    _uiState.update {
                        ProductUiState.Empty
                    }
                }
            }
        }
    }

    fun onQueryChanged(query: String) {
        if (_uiState.value !is ProductUiState.Success) return

        val filtered = if (query.isBlank()) allItems
        else allItems.filter { it.name.contains(query, ignoreCase = true) }

        _uiState.value = ProductUiState.Success(
            result = filtered,
            query = query
        )
    }

    fun onItemClick(item: Product) {
        if (_uiState.value is ProductUiState.Loading) {
            return
        }
        _uiState.value = ProductUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = syncProductUseCase(SyncOperation.UpsertLocal(item))
                when (result) {
                    is SyncResult.Error -> {
                        _uiState.value = ProductUiState.Error(result.exception.message.orEmpty())
                    }

                    is SyncResult.Success<*> -> {
                        fetchLocal()
                    }
                }
            } catch (e: Exception) {
                _uiState.value = ProductUiState.Error(e.message.orEmpty())
            }
        }
    }

    fun fetchRemote() {
        if (_uiState.value is ProductUiState.Loading) {
            return
        }
        _uiState.value = ProductUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = syncProductUseCase(SyncOperation.RemoteToLocal())
                when (result) {
                    is SyncResult.Error -> {
                        _uiState.value = ProductUiState.Error(result.exception.message.orEmpty())
                    }

                    is SyncResult.Success<*> -> {
                        fetchLocal()
                    }
                }
            } catch (e: Exception) {
                _uiState.value = ProductUiState.Error(e.message.orEmpty())
            }
        }
    }
}