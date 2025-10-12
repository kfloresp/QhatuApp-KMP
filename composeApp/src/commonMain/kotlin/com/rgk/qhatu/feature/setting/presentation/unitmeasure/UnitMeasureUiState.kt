package com.rgk.qhatu.feature.setting.presentation.unitmeasure

import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure

sealed class UnitMeasureUiState {
    data class Success(val result: List<UnitMeasure>, val query: String = "") : UnitMeasureUiState()
    object Loading : UnitMeasureUiState()
    object Empty : UnitMeasureUiState()
    data class Error(val message: String) : UnitMeasureUiState()
}

sealed class UnitMeasureFormUiState {
    object Idle : UnitMeasureFormUiState()
    data class Upsert(
        val unitMeasure: UnitMeasure,
        val isValidForm: Boolean = false,
        val isLoading: Boolean = false,
    ) : UnitMeasureFormUiState()

    data class Error(val message: String) : UnitMeasureFormUiState()
}