package com.rgk.qhatu.feature.setting.presentation.store

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.button.ButtonFlotableAction
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.loading.LoadingSection
import com.rgk.qhatu.feature.setting.domain.model.Store
import com.rgk.qhatu.navigation.ProvideFabAction
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_setting_cancel
import qhatuapp.composeapp.generated.resources.tx_setting_store_confirm_save
import qhatuapp.composeapp.generated.resources.tx_setting_store_confirm_save_subtitle
import qhatuapp.composeapp.generated.resources.tx_setting_store_confirm_save_title
import qhatuapp.composeapp.generated.resources.tx_setting_store_save_changes

@Serializable
data object StoreDestination

internal fun NavGraphBuilder.storeDestination() {
    composable<StoreDestination> {
        val viewModel: StoreViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        var selectedButtonConfirm by remember { mutableStateOf(false) }
        var storeValue by remember { mutableStateOf(Store()) }

        ProvideFabAction {
            if (uiState is StoreUiState.Success) {
                ButtonFlotableAction(
                    label = stringResource(Res.string.tx_setting_store_save_changes),
                    iconVector = Icons.Filled.Save,
                ) {
                    selectedButtonConfirm = true
                }
            }
        }

        when (uiState) {
            is StoreUiState.Error -> {
                val error = (uiState as StoreUiState.Error).message
                ErrorSection(error)
            }

            StoreUiState.Loading -> {
                LoadingSection()
            }

            is StoreUiState.Success -> {
                val store = (uiState as StoreUiState.Success).result
                StoreScreen(
                    store = store,
                    onStoreChange = { storeValue = it },
                )
            }
        }

        if (selectedButtonConfirm) {
            ConfirmDialog(
                title = stringResource(Res.string.tx_setting_store_confirm_save_title),
                description = stringResource(
                    Res.string.tx_setting_store_confirm_save_subtitle
                ),
                primaryButtonText = stringResource(Res.string.tx_setting_store_confirm_save),
                onPrimaryClick = {
                    viewModel.onItemClick(
                        storeValue
                    )
                    selectedButtonConfirm = false
                },
                secondaryButtonText = stringResource(Res.string.tx_setting_cancel),
                onSecondaryClick = {
                    selectedButtonConfirm = false
                },
                onDismiss = {
                    selectedButtonConfirm = false
                }
            )
        }
    }
}