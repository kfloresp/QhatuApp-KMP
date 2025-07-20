package com.rgk.qhatu.feature.setting.presentation.unitmeasure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import com.rgk.qhatu.feature.setting.domain.usecase.GetCategoriesUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.GetUnitMeasureStatsUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.GetUnitsMeasureUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncCategoryUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncUnitMeasureUseCase
import com.rgk.qhatu.feature.setting.presentation.category.CategoryUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UnitMeasureViewModel(
    private val syncUnitMeasureUseCase: SyncUnitMeasureUseCase,
    private val getUnitsMeasureUseCase: GetUnitsMeasureUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UnitMeasureUiState>(UnitMeasureUiState.Loading)
    val uiState: StateFlow<UnitMeasureUiState> = _uiState.asStateFlow()

    init {
        fetchLocal()
    }

    fun fetchLocal() {
        _uiState.value = UnitMeasureUiState.Loading
        viewModelScope.launch {
            val result = getUnitsMeasureUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _uiState.value = UnitMeasureUiState.Error(result.exception.message.orEmpty())
                    println(result.exception.message.orEmpty())
                }

                is SyncResult.Success<*> -> {
                    _uiState.value = UnitMeasureUiState.Success(
                        result = result.data as List<UnitMeasure>
                    )
                }
            }
        }
    }

    fun onQueryChanged(query: String) {

    }

    fun onItemClick(item: UnitMeasure) {
        // Por implementar
    }

    fun onEditClick(item: UnitMeasure) {
        // Por implementar
    }

    fun onDeleteClick(item: UnitMeasure) {
        // Por implementar
    }

    fun fetchRemote() {
        if (_uiState.value is UnitMeasureUiState.Loading) {
            return
        }
        _uiState.value = UnitMeasureUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = syncUnitMeasureUseCase(SyncOperation.Download())
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
