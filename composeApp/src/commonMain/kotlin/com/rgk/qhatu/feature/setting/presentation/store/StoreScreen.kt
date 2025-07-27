package com.rgk.qhatu.feature.setting.presentation.store

import androidx.compose.runtime.Composable
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.loading.LoadingSection
import com.rgk.qhatu.feature.setting.domain.model.Store
import com.rgk.qhatu.feature.setting.presentation.store.component.StoreForm

@Composable
fun StoreScreen(
    uiState: StoreUiState,
    onStoreChange: (Store) -> Unit
) {
    when (uiState) {
        is StoreUiState.Error -> {
            val error = uiState.message
            ErrorSection(error)
        }

        StoreUiState.Loading -> {
            LoadingSection()
        }

        is StoreUiState.Success -> {
            val store = uiState.result
            StoreForm(store = store, onSaveClick = onStoreChange)
        }
    }
}