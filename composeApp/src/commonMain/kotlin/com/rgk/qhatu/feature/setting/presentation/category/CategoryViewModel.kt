package com.rgk.qhatu.feature.setting.presentation.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.usecase.GetCategoriesUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncCategoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val syncCategoryUseCase: SyncCategoryUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CategoryUiState>(CategoryUiState.Loading)
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    init {
        println(">> CategoryViewModel creado: ${this.hashCode()}")
        getCategories()
    }

    fun getCategories() {
        println("Get Categories....")
        _uiState.value = CategoryUiState.Loading
        viewModelScope.launch {
            val result = getCategoriesUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _uiState.value = CategoryUiState.Error(result.exception.message.orEmpty())
                    println("Get Categories....Error")
                    println(result.exception.message.orEmpty())
                }

                is SyncResult.Success<*> -> {
                    println("Get Categories....Success")
                    _uiState.value = CategoryUiState.Success(
                        categories = result.data as List<Category>
                    )
                }
            }
        }
    }

    fun onQueryChanged(query: String) {

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
        _uiState.value = CategoryUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = syncCategoryUseCase(SyncOperation.Download())
                when (result) {
                    is SyncResult.Error -> {
                        _uiState.value = CategoryUiState.Error(result.exception.message.orEmpty())
                        println("Sync Categories Error....")
                        println("ERROR: " + result.exception.message.orEmpty())
                    }

                    is SyncResult.Success<*> -> {
                        delay(2500L)
                        getCategories()
                    }
                }
            } catch (e: Exception) {
                _uiState.value = CategoryUiState.Error(e.message.orEmpty())
                println("Sync Categories Exception....")
                println("EXCEPTION: " + e.message.orEmpty())
            }
        }
    }
}
