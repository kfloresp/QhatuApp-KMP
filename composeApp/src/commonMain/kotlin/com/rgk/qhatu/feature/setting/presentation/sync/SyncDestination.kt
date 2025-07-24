package com.rgk.qhatu.feature.setting.presentation.sync

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Update
import androidx.compose.material.icons.outlined.Backup
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.feature.setting.presentation.sync.component.SyncsOption
import com.rgk.qhatu.feature.setting.presentation.sync.component.getSyncsOptions
import com.rgk.qhatu.navigation.ProvideAppBarActions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object SyncDestination

internal fun NavGraphBuilder.syncDestination() {
    composable<SyncDestination> {
        val viewModel: SyncViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val syncOptions = MutableStateFlow(getSyncsOptions())
        val options by syncOptions.collectAsState()
        ProvideAppBarActions {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Update, contentDescription = null)
            }
        }
        when (uiState) {
            is SyncUiState.Error -> {
                val index = (uiState as SyncUiState.Error).index
                val message = (uiState as SyncUiState.Error).message
                syncOptions.update { list ->
                    list.toMutableList().apply {
                        this[index] = this[index].copy(subtitle = message)
                    }
                }
            }

            is SyncUiState.Loading -> {
                val index = (uiState as SyncUiState.Loading).index
                syncOptions.update { list ->
                    list.toMutableList().apply {
                        this[index] = this[index].copy(icon = Icons.Outlined.Update)
                    }
                }
            }

            is SyncUiState.Success -> {
                val index = (uiState as SyncUiState.Success).index
                syncOptions.update { list ->
                    list.toMutableList().apply {
                        this[index] = this[index].copy(subtitle = "Sincronización exitosa")
                    }
                }
            }

            SyncUiState.Idle -> Unit
        }
        SyncScreen(
            onOptionClick = { syncType, index ->
                viewModel.sync(syncType, index)
            },
            syncOptions = options
        )
    }
}