package com.rgk.qhatu.feature.setting.presentation.store

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.feature.setting.domain.model.Store
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_setting_cancel
import qhatuapp.composeapp.generated.resources.tx_setting_store_confirm_save
import qhatuapp.composeapp.generated.resources.tx_setting_store_confirm_save_subtitle
import qhatuapp.composeapp.generated.resources.tx_setting_store_confirm_save_title

@Serializable
data object StoreDestination

internal fun NavGraphBuilder.storeDestination() {
    composable<StoreDestination> {
        val viewModel: StoreViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        var storeValue by remember { mutableStateOf<Store?>(null) }

        StoreScreen(
            uiState = uiState,
            onStoreChange = { storeValue = it },
        )

        storeValue?.let {
            ConfirmDialog(
                title = stringResource(Res.string.tx_setting_store_confirm_save_title),
                description = stringResource(
                    Res.string.tx_setting_store_confirm_save_subtitle
                ),
                primaryButtonText = stringResource(Res.string.tx_setting_store_confirm_save),
                onPrimaryClick = {
                    viewModel.onItemClick(
                        it
                    )
                    storeValue = null
                },
                secondaryButtonText = stringResource(Res.string.tx_setting_cancel),
                onSecondaryClick = {
                    storeValue = null
                },
                onDismiss = {
                    storeValue = null
                }
            )
        }
    }
}