package com.rgk.qhatu.feature.setting.presentation.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Store
import com.rgk.qhatu.feature.setting.domain.usecase.GetStoreUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncStoreUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StoreViewModel(
    private val getStoreUseCase: GetStoreUseCase,
    private val syncStoreUseCase: SyncStoreUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<StoreUiState>(StoreUiState.Loading)
    val uiState: StateFlow<StoreUiState> = _uiState.asStateFlow()

    init {
        fetchLocal()
    }

    fun fetchLocal() {
        viewModelScope.launch {
            _uiState.update {
                StoreUiState.Loading
            }
            val result = getStoreUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        StoreUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<*> -> {
                    _uiState.update {
                        StoreUiState.Success(
                            result = (result.data as Store)
                        )
                    }
                }
            }
        }
    }

    fun onItemClick(item: Store) {
        if (_uiState.value is StoreUiState.Loading) {
            return
        }
        _uiState.value = StoreUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = syncStoreUseCase(SyncOperation.UpsertLocal(item))
                when (result) {
                    is SyncResult.Error -> {
                        _uiState.value =
                            StoreUiState.Error(result.exception.message.orEmpty())
                    }

                    is SyncResult.Success<*> -> {
                        fetchLocal()
                    }
                }
            } catch (e: Exception) {
                _uiState.value = StoreUiState.Error(e.message.orEmpty())
            }
        }
    }

    fun onFieldChange(change: (Store) -> Store) {

    }
}