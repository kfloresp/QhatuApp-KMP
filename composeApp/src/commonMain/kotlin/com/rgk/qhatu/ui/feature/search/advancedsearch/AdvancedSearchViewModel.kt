package com.rgk.qhatu.ui.feature.search.advancedsearch

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.model.Product
import com.rgk.qhatu.domain.usecase.product.GetProductFromQueryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class AdvancedSearchViewModel(
    private val query: String,
    private val searchType: Int,
    private val getProductFromQueryUseCase: GetProductFromQueryUseCase,
) : ViewModel() {

    private val _uiState = mutableStateOf<AdvancedSearchUiState>(AdvancedSearchUiState.Loading)
    val uiState: State<AdvancedSearchUiState> = _uiState

    fun initSearch(){
        viewModelScope.launch(Dispatchers.IO) {
                _uiState.value = AdvancedSearchUiState.Loading
                when(val results = getProductFromQueryUseCase.invoke(query, searchType)){
                    is SyncResult.Error -> _uiState.value = AdvancedSearchUiState.Error(results.exception.message.orEmpty())
                    is SyncResult.Success<List<Product>> -> {
                        _uiState.value = when {
                            results.data.isEmpty() -> AdvancedSearchUiState.Error("No se encontraron resultados.")
                            else -> AdvancedSearchUiState.Success(results.data)
                        }
                    }
                }
        }
    }
}

sealed class AdvancedSearchUiState {
    data object Loading : AdvancedSearchUiState()
    data class Success(val products: List<Product>) : AdvancedSearchUiState()
    data class Error(val message: String) : AdvancedSearchUiState()
}