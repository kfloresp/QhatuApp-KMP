package com.rgk.qhatu.feature.setting.presentation.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.usecase.GetCategoriesUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncCategoryUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.UpdateCategoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val syncCategoryUseCase: SyncCategoryUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase,
) : ViewModel() {
    private val DELAY_TIME = 1500L
    private var allItems: List<Category> = emptyList()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()
    var isSearching by mutableStateOf(false)
        private set
    private val _uiState = MutableStateFlow<CategoryUiState>(CategoryUiState.Loading)
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

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
        _uiState.value = CategoryUiState.Loading
        delay(DELAY_TIME)
        val result = getCategoriesUseCase()
        when (result) {
            is SyncResult.Error -> {
                _uiState.value = CategoryUiState.Error(result.exception.message.orEmpty())
                println(result.exception.message.orEmpty())
            }

            is SyncResult.Success<*> -> {
                allItems = result.data as List<Category>
                _uiState.value = CategoryUiState.Success(
                    result = allItems
                )
            }
        }
    }
    fun onQueryChanged(query: String) {
        if (_uiState.value !is CategoryUiState.Success) return

        val filtered = if (query.isBlank()) allItems
        else allItems.filter { it.nombre.contains(query, ignoreCase = true) }

        _uiState.value = CategoryUiState.Success(
            result = filtered,
            query = query
        )
    }

    fun onItemClick(item: Category) {
        if (_uiState.value is CategoryUiState.Loading) {
            return
        }
        _uiState.value = CategoryUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = updateCategoryUseCase(item)
                when (result) {
                    is SyncResult.Error -> {
                        _uiState.value = CategoryUiState.Error(result.exception.message.orEmpty())
                    }

                    is SyncResult.Success<*> -> {
                        fetchLocal()
                    }
                }
            } catch (e: Exception) {
                _uiState.value = CategoryUiState.Error(e.message.orEmpty())
            }
        }
    }

    fun fetchRemote() {
        if (_uiState.value is CategoryUiState.Loading) {
            return
        }
        _uiState.value = CategoryUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = syncCategoryUseCase(SyncOperation.Download())
                when (result) {
                    is SyncResult.Error -> {
                        _uiState.value = CategoryUiState.Error(result.exception.message.orEmpty())
                    }

                    is SyncResult.Success<*> -> {
                        fetchLocal()
                    }
                }
            } catch (e: Exception) {
                _uiState.value = CategoryUiState.Error(e.message.orEmpty())
            }
        }
    }
}
