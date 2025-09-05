package com.rgk.qhatu.feature.setting.presentation.brand

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Brand
import com.rgk.qhatu.feature.setting.domain.usecase.GetBrandsUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.UpsertBrandUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BrandViewModel(
    private val upsertBrandUseCase: UpsertBrandUseCase,
    private val getBrandsUseCase: GetBrandsUseCase
) : ViewModel() {
    private var allItems: List<Brand> = emptyList()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()
    private val _uiState = MutableStateFlow<BrandUiState>(BrandUiState.Loading)
    val uiState: StateFlow<BrandUiState> = _uiState.asStateFlow()

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
            BrandUiState.Loading
        }
        val result = getBrandsUseCase()
        when (result) {
            is SyncResult.Error -> {
                _uiState.update {
                    BrandUiState.Error(result.exception.message.orEmpty())
                }
            }

            is SyncResult.Success<*> -> {
                allItems = result.data as List<Brand>
                if (allItems.isNotEmpty()) {
                    _uiState.update {
                        BrandUiState.Success(
                            result = allItems
                        )
                    }
                } else {
                    _uiState.update {
                        BrandUiState.Empty
                    }
                }
            }
        }
    }

    fun onQueryChanged(query: String) {
        if (_uiState.value !is BrandUiState.Success) return

        val filtered = if (query.isBlank()) allItems
        else allItems.filter { it.name.contains(query, ignoreCase = true) }

        _uiState.value = BrandUiState.Success(
            result = filtered,
            query = query
        )
    }

    fun onItemClick(data: Brand) {
        if (_uiState.value is BrandUiState.Loading) {
            return
        }
        _uiState.value = BrandUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = upsertBrandUseCase(data)
                when (result) {
                    is SyncResult.Error -> {
                        _uiState.value = BrandUiState.Error(result.exception.message.orEmpty())
                    }

                    is SyncResult.Success<*> -> {
                        fetchLocal()
                    }
                }
            } catch (e: Exception) {
                _uiState.value = BrandUiState.Error(e.message.orEmpty())
            }
        }
    }
}
