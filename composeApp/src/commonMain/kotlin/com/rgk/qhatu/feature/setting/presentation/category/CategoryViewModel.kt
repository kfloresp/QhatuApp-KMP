package com.rgk.qhatu.feature.setting.presentation.category

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.usecase.GetCategoriesUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncCategoryUseCase
import com.rgk.qhatu.feature.setting.presentation.sync.SyncState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class CategoryViewModel(
    private val syncCategoryUseCase: SyncCategoryUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    init {
        getCategories()
    }

    fun getCategories(){
        println("Get Categories....")
        viewModelScope.launch {
            val result = getCategoriesUseCase()
            when (result){
                is SyncResult.Error -> {
                    println(result.exception.message.orEmpty())
                }
                is SyncResult.Success<*> -> {
                    _uiState.value = CategoryUiState(categories = result.data as List<Category>)
                }
            }
        }
    }
    fun onQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
    }

    fun onItemClick(category: Category) {
        // Por implementar
    }

    fun onEditClick(category: Category) {
        // Por implementar
    }

    fun onDeleteClick(category: Category) {
        // Por implementar
    }

    fun syncCategories() {
        println("Sync Categories....")
        viewModelScope.launch(Dispatchers.IO) {
            val result = syncCategoryUseCase(SyncOperation.Download())
            when (result) {
                is SyncResult.Error -> {
                    println(result.exception.message.orEmpty())
                }

                is SyncResult.Success<*> -> {
                    getCategories()
                }
            }
        }
    }
}
