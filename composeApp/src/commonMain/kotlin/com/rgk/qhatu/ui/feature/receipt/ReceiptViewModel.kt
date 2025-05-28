package com.rgk.qhatu.ui.feature.receipt

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.model.Client
import com.rgk.qhatu.domain.usecase.client.GetClientProviderUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ReceiptViewModel(private val clientProviderUseCase: GetClientProviderUseCase): ViewModel() {
    private val _uiState = mutableStateOf<ReceiptState>(ReceiptState.Idle)
    val uiState: State<ReceiptState> = _uiState

    fun searchProvider(query: String) {
        _uiState.value = ReceiptState.Loading
        viewModelScope.launch(Dispatchers.IO) {
                when (val results = clientProviderUseCase(query, isProvider = 1)){
                    is SyncResult.Error -> _uiState.value = ReceiptState.Error(results.exception.message.orEmpty())
                    is SyncResult.Success<List<Client>> -> _uiState.value = when {
                        results.data.isEmpty() -> ReceiptState.Error("No se encontraron proveedores")
                        results.data.size == 1 -> ReceiptState.Single(results.data.first())
                        else -> ReceiptState.Multiple(results.data)
                    }
                }
        }
    }

    fun clearSearch() {
        _uiState.value = ReceiptState.Idle
    }
}

sealed class ReceiptState {
    data object Idle : ReceiptState()
    data object Loading : ReceiptState()
    data class Single(val provider: Client) : ReceiptState()
    data class Multiple(val providers: List<Client>) : ReceiptState()
    data class Error(val message: String) : ReceiptState()
}