package com.rgk.qhatu.feature.setting.presentation.sync

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.feature.setting.presentation.sync.component.getSyncsOptions
import com.rgk.qhatu.navigation.ProvideAppBar
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object SyncDestination

internal fun NavGraphBuilder.syncDestination() {
    composable<SyncDestination> {
        val viewModel: SyncViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()

        ProvideAppBar(actions = {
            IconButton(onClick = {
                viewModel.syncAll()
            }) {
                Icon(Icons.Default.Update, contentDescription = null)
            }
        })

        val currentOptions = remember(uiState.itemStates) {
            getSyncsOptions().mapIndexed { index, item ->
                when (val state = uiState.itemStates.getOrNull(index)) {
                    is SyncItemState.Loading -> item.copy(loading = true)
                    is SyncItemState.Success -> item.copy(subtitle = state.message, loading = false)
                    is SyncItemState.Error -> item.copy(subtitle = state.message, loading = false)
                    else -> item
                }
            }
        }
        SyncScreen(
            onOptionClick = { syncType, index ->
                viewModel.sync(syncType, index)
            },
            syncOptions = currentOptions
        )
    }
}