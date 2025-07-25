package com.rgk.qhatu.feature.setting.presentation.unitmeasure

import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure

sealed class UnitMeasureUiState {
    data class Success(val result: List<UnitMeasure>, val query: String = "") : UnitMeasureUiState()
    object Loading : UnitMeasureUiState()
    object Empty : UnitMeasureUiState()
    data class Error(val message: String) : UnitMeasureUiState()
}