package com.rgk.qhatu.feature.setting.presentation.sync

sealed class SyncUiState {
    data object Idle : SyncUiState()
    data class Success(val index: Int) : SyncUiState()
    data class Loading(val index: Int) : SyncUiState()
    data class Error(val index: Int, val message: String) : SyncUiState()
}