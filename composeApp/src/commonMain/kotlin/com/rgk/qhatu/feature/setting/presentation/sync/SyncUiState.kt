package com.rgk.qhatu.feature.setting.presentation.sync

sealed class SyncUiState {
    object Loading : SyncUiState()
    object Empty : SyncUiState()
    data class Error(val message: String) : SyncUiState()
}