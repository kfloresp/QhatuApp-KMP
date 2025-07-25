package com.rgk.qhatu.feature.search.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.domain.usecase.GetProductFromQueryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getProductFromQueryUseCase: GetProductFromQueryUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<SearchResultState>(SearchResultState.Idle)
    val uiState: StateFlow<SearchResultState> = _uiState


    fun searchProducts(query: String, searchType: Int) {
        if (query.length < 3) {
            _uiState.value = SearchResultState.Error("Debe ingresar al menos 3 caracteres.")
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = SearchResultState.Loading
            when (val results = getProductFromQueryUseCase.invoke(query, searchType)) {
                is SyncResult.Error -> _uiState.value = SearchResultState.Error(results.exception.message.orEmpty())
                is SyncResult.Success<List<Product>> -> {
                    _uiState.value = when {
                        results.data.isEmpty() -> SearchResultState.Error("No se encontraron resultados.")
                        results.data.size == 1 -> SearchResultState.SingleResult(results.data.first())
                        else -> SearchResultState.MultipleResults(results.data)
                    }
                }
            }
        }
    }

    fun clearSearchResult() {
        _uiState.value = SearchResultState.Idle
    }

    fun setSelectedProduct(product: Product) {
        _uiState.value = SearchResultState.SingleResult(product)
    }

}

sealed class SearchResultState {
    data object Idle : SearchResultState()
    data object Loading : SearchResultState()
    data class SingleResult(val product: Product) : SearchResultState()
    data class MultipleResults(val products: List<Product>) : SearchResultState()
    data class Error(val message: String) : SearchResultState()
}
