package com.rgk.qhatu.feature.setting.presentation.store

import com.rgk.qhatu.feature.setting.domain.model.Store

sealed class StoreUiState {
    data class Success(val result: Store, val isValidForm: Boolean = false) : StoreUiState()
    object Loading : StoreUiState()
    data class Error(val message: String) : StoreUiState()
}