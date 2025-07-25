package com.rgk.qhatu.feature.setting.presentation.sync

data class SyncUiState(
    val itemStates: List<SyncItemState> = emptyList()
)

sealed class SyncItemState {
    data object Idle : SyncItemState()
    data object Loading : SyncItemState()
    data class Success(val message: String = "Sincronización exitosa") : SyncItemState()
    data class Error(val message: String) : SyncItemState()
}