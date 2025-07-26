package com.rgk.qhatu.feature.sale.presentation.receipt.searchprovider

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.Customer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class SearchProviderViewModel(
    private val query: String
) : ViewModel() {
    private val _uiState = mutableStateOf<SearchProviderUiState>(SearchProviderUiState.Loading)
    val uiState: State<SearchProviderUiState> = _uiState

    fun initSearch(){
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = SearchProviderUiState.Loading
//            when(val results = getProviderUseCase.invoke(query)){
//                is SyncResult.Error -> _uiState.value = SearchProviderUiState.Error(results.exception.message.orEmpty())
//                is SyncResult.Success<List<Customer>> -> {
//                    _uiState.value = when {
//                        results.data.isEmpty() -> SearchProviderUiState.Error("No se encontraron resultados.")
//                        else -> SearchProviderUiState.Success(results.data)
//                    }
//                }
//            }
        }
    }
}

sealed class SearchProviderUiState {
    data object Loading : SearchProviderUiState()
    data class Success(val customers: List<Customer>) : SearchProviderUiState()
    data class Error(val message: String) : SearchProviderUiState()
}