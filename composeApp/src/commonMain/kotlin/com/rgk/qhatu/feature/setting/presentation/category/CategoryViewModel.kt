package com.rgk.qhatu.feature.setting.presentation.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.model.Store
import com.rgk.qhatu.feature.setting.domain.usecase.GetCategoriesUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.UpsertCategoryUseCase
import com.rgk.qhatu.feature.setting.presentation.store.StoreUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val upsertCategoryUseCase: UpsertCategoryUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : ViewModel() {

    private val _listUiState = MutableStateFlow<CategoryUiState>(CategoryUiState.Loading)
    val listUiState: StateFlow<CategoryUiState> = _listUiState.asStateFlow()

    private val _formUiState = MutableStateFlow<CategoryFormUiState>(CategoryFormUiState.Idle)
    val formUiState: StateFlow<CategoryFormUiState> = _formUiState.asStateFlow()
    private var allItems: List<Category> = emptyList()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    init {
        onPullRefresh()
    }

    fun onPullRefresh() {
        _isRefreshing.value = true
        viewModelScope.launch {
            fetchLocal()
            _isRefreshing.value = false
        }
    }

    private suspend fun fetchLocal() {
        _listUiState.value = CategoryUiState.Loading
        delay(2000L)
        when (val result = getCategoriesUseCase()) {
            is SyncResult.Error -> {
                _listUiState.value = CategoryUiState.Error(result.exception.message.orEmpty())
            }

            is SyncResult.Success<*> -> {
                allItems = result.data as List<Category>
                _listUiState.value = if (allItems.isNotEmpty()) {
                    CategoryUiState.Success(allItems)
                } else {
                    CategoryUiState.Empty
                }
            }
        }
    }

    fun onQueryChanged(query: String) {
        val current = _listUiState.value as? CategoryUiState.Success ?: return
        val filtered = if (query.isBlank()) allItems
        else allItems.filter { it.name.contains(query, ignoreCase = true) }
        _listUiState.value = current.copy(result = filtered, query = query)
    }

    fun startUpsert(category: Category? = null) {
        val base = category ?: Category()
        _formUiState.value = CategoryFormUiState.Upsert(base, validateFields(base))
    }

    fun onFieldChange(update: Category.() -> Category) {
        val current = _formUiState.value as? CategoryFormUiState.Upsert ?: return
        val updated = current.category.update()
        _formUiState.value = CategoryFormUiState.Upsert(updated, validateFields(updated))
    }

    fun onUpsertCategory(category: Category) {
        _formUiState.value = CategoryFormUiState.Loading
        viewModelScope.launch {
        delay(2000L)
            when (val result = upsertCategoryUseCase(category)) {
                is SyncResult.Error -> {
                    _formUiState.value =
                        CategoryFormUiState.Error(result.exception.message.orEmpty())
                }

                is SyncResult.Success<*> -> {
                    fetchLocal()
                    _formUiState.value = CategoryFormUiState.Idle
                }
            }
        }
    }

    fun cancelForm() {
        _formUiState.value = CategoryFormUiState.Idle
    }

    private fun validateFields(category: Category): Boolean {
        return category.name.isNotBlank()
    }
}

