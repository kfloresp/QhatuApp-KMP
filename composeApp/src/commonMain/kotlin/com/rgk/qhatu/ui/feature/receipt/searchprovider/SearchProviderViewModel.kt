package com.rgk.qhatu.ui.feature.receipt.searchprovider

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.model.Client
import com.rgk.qhatu.domain.usecase.client.GetProviderUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class SearchProviderViewModel(
    private val getProviderUseCase: GetProviderUseCase,
    private val query: String
) : ViewModel() {
    private val _uiState = mutableStateOf<SearchProviderUiState>(SearchProviderUiState.Loading)
    val uiState: State<SearchProviderUiState> = _uiState

    fun initSearch(){
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = SearchProviderUiState.Loading
            when(val results = getProviderUseCase.invoke(query)){
                is SyncResult.Error -> _uiState.value = SearchProviderUiState.Error(results.exception.message.orEmpty())
                is SyncResult.Success<List<Client>> -> {
                    _uiState.value = when {
                        results.data.isEmpty() -> SearchProviderUiState.Error("No se encontraron resultados.")
                        else -> SearchProviderUiState.Success(results.data)
                    }
                }
            }
        }
    }
}

sealed class SearchProviderUiState {
    data object Loading : SearchProviderUiState()
    data class Success(val clients: List<Client>) : SearchProviderUiState()
    data class Error(val message: String) : SearchProviderUiState()
}