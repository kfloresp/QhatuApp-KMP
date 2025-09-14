package com.rgk.qhatu.feature.setting.presentation.unitmeasure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import com.rgk.qhatu.feature.setting.domain.usecase.GetUnitsMeasureUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.UpsertUnitMeasureUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val DELAY_TIME = 500L

class UnitMeasureViewModel(
    private val upsertUnitMeasureUseCase: UpsertUnitMeasureUseCase,
    private val getUnitsMeasureUseCase: GetUnitsMeasureUseCase,
) : ViewModel() {
    private var allItems: List<UnitMeasure> = emptyList()
    private val _listUiState = MutableStateFlow<UnitMeasureUiState>(UnitMeasureUiState.Loading)
    val listUiState: StateFlow<UnitMeasureUiState> = _listUiState.asStateFlow()

    private val _formUiState = MutableStateFlow<UnitMeasureFormUiState>(UnitMeasureFormUiState.Idle)
    val formUiState: StateFlow<UnitMeasureFormUiState> = _formUiState.asStateFlow()

    init {
        fetchLocal()
    }

    private fun fetchLocal() {
        viewModelScope.launch {
            _listUiState.update {
                UnitMeasureUiState.Loading
            }
            delay(DELAY_TIME)
            when (val result = getUnitsMeasureUseCase()) {
                is SyncResult.Error -> {
                    _listUiState.update {
                        UnitMeasureUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<*> -> {
                    allItems = result.data as List<UnitMeasure>
                    if (allItems.isNotEmpty()) {
                        _listUiState.update {
                            UnitMeasureUiState.Success(
                                result = allItems
                            )
                        }
                    } else {
                        _listUiState.update {
                            UnitMeasureUiState.Empty
                        }
                    }
                }
            }
        }
    }

    fun onQueryChanged(query: String) {
        val current = _listUiState.value as? UnitMeasureUiState.Success ?: return
        val filtered = if (query.isBlank()) allItems
        else allItems.filter { it.name.contains(query, ignoreCase = true) }
        _listUiState.value = current.copy(result = filtered, query = query)
    }

    fun startUpsert(unitMeasure: UnitMeasure? = null) {
        val base = unitMeasure ?: UnitMeasure()
        _formUiState.value = UnitMeasureFormUiState.Upsert(base, validateFields(base))
    }

    fun onFieldChange(update: UnitMeasure.() -> UnitMeasure) {
        val current = _formUiState.value as? UnitMeasureFormUiState.Upsert ?: return
        val updated = current.unitMeasure.update()
        _formUiState.value = UnitMeasureFormUiState.Upsert(updated, validateFields(updated))
    }

    fun onUpsertCategory(unitMeasure: UnitMeasure) {
        val current = _formUiState.value as? UnitMeasureFormUiState.Upsert ?: return
        _formUiState.value = current.copy(isLoading = true)
        viewModelScope.launch {
            delay(DELAY_TIME)
            when (val result = upsertUnitMeasureUseCase(unitMeasure)) {
                is SyncResult.Error -> {
                    _formUiState.value =
                        UnitMeasureFormUiState.Error(result.exception.message.orEmpty())
                }

                is SyncResult.Success<*> -> {
                    _formUiState.value = UnitMeasureFormUiState.Idle
                    fetchLocal()
                }
            }
        }
    }

    fun cancelForm() {
        _formUiState.value = UnitMeasureFormUiState.Idle
    }

    private fun validateFields(unitMeasure: UnitMeasure): Boolean {
        return unitMeasure.name.isNotBlank()
    }
}
