package com.rgk.qhatu.feature.setting.presentation.unitmeasure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import com.rgk.qhatu.feature.setting.domain.usecase.GetUnitsMeasureUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncUnitMeasureUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UnitMeasureViewModel(
    private val syncUnitMeasureUseCase: SyncUnitMeasureUseCase,
    private val getUnitsMeasureUseCase: GetUnitsMeasureUseCase,
) : ViewModel() {
    private val DELAY_TIME = 500L
    private var allItems: List<UnitMeasure> = emptyList()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()
    private val _uiState = MutableStateFlow<UnitMeasureUiState>(UnitMeasureUiState.Loading)
    val uiState: StateFlow<UnitMeasureUiState> = _uiState.asStateFlow()

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
            UnitMeasureUiState.Loading
        }
        delay(DELAY_TIME)
        val result = getUnitsMeasureUseCase()
        when (result) {
            is SyncResult.Error -> {
                _uiState.update {
                    UnitMeasureUiState.Error(result.exception.message.orEmpty())
                }
            }

            is SyncResult.Success<*> -> {
                allItems = result.data as List<UnitMeasure>
                if (allItems.isNotEmpty()) {
                    _uiState.update {
                        UnitMeasureUiState.Success(
                            result = allItems
                        )
                    }
                } else {
                    _uiState.update {
                        UnitMeasureUiState.Empty
                    }
                }
            }
        }
    }

    fun onQueryChanged(query: String) {
        if (_uiState.value !is UnitMeasureUiState.Success) return

        val filtered = if (query.isBlank()) allItems
        else allItems.filter { it.name.contains(query, ignoreCase = true) }

        _uiState.value = UnitMeasureUiState.Success(
            result = filtered,
            query = query
        )
    }

    fun onItemClick(item: UnitMeasure) {
        if (_uiState.value is UnitMeasureUiState.Loading) {
            return
        }
        _uiState.value = UnitMeasureUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = syncUnitMeasureUseCase(SyncOperation.UpsertLocal(item))
                when (result) {
                    is SyncResult.Error -> {
                        _uiState.value = UnitMeasureUiState.Error(result.exception.message.orEmpty())
                    }

                    is SyncResult.Success<*> -> {
                        fetchLocal()
                    }
                }
            } catch (e: Exception) {
                _uiState.value = UnitMeasureUiState.Error(e.message.orEmpty())
            }
        }
    }

    fun fetchRemote() {
        if (_uiState.value is UnitMeasureUiState.Loading) {
            return
        }
        _uiState.value = UnitMeasureUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = syncUnitMeasureUseCase(SyncOperation.RemoteToLocal())
                when (result) {
                    is SyncResult.Error -> {
                        _uiState.value = UnitMeasureUiState.Error(result.exception.message.orEmpty())
                    }

                    is SyncResult.Success<*> -> {
                        fetchLocal()
                    }
                }
            } catch (e: Exception) {
                _uiState.value = UnitMeasureUiState.Error(e.message.orEmpty())
            }
        }
    }
}
