package com.rgk.qhatu.feature.setting.presentation.brand

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Brand
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.usecase.GetBrandsUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.GetCategoriesUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncBrandUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BrandViewModel(
    private val syncBrandUseCase: SyncBrandUseCase,
    private val getBrandsUseCase: GetBrandsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BrandUiState>(BrandUiState.Loading)
    val uiState: StateFlow<BrandUiState> = _uiState.asStateFlow()

    init {
        fetchLocal()
    }

    fun fetchLocal() {
        _uiState.value = BrandUiState.Loading
        viewModelScope.launch {
            val result = getBrandsUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _uiState.value = BrandUiState.Error(result.exception.message.orEmpty())
                    println(result.exception.message.orEmpty())
                }

                is SyncResult.Success<*> -> {
                    _uiState.value = BrandUiState.Success(
                        result = result.data as List<Brand>
                    )
                }
            }
        }
    }

    fun onQueryChanged(query: String) {

    }

    fun onItemClick(item: Brand) {
        // Por implementar
    }

    fun onEditClick(item: Brand) {
        // Por implementar
    }

    fun onDeleteClick(item: Brand) {
        // Por implementar
    }

    fun fetchRemote() {
        if (_uiState.value is BrandUiState.Loading){
            return
        }
        _uiState.value = BrandUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = syncBrandUseCase(SyncOperation.Download())
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
