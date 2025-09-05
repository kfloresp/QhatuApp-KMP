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
import com.rgk.qhatu.navigation.ProvideAppBar
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_cancel
import qhatuapp.composeapp.generated.resources.tx_global_confirm_delete
import qhatuapp.composeapp.generated.resources.tx_global_confirm_delete_photo_message
import qhatuapp.composeapp.generated.resources.tx_global_confirm_save
import qhatuapp.composeapp.generated.resources.tx_global_confirm_save_subtitle
import qhatuapp.composeapp.generated.resources.tx_global_confirmation

@Serializable
data object StoreDestination

internal fun NavGraphBuilder.storeDestination() {
    composable<StoreDestination> {
        val viewModel: StoreViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        var storeValue by remember { mutableStateOf<Store?>(null) }
        var imagePathDeleted by remember { mutableStateOf<String?>(null) }

        StoreScreen(
            uiState = uiState,
            onSaveClick = { store ->
                storeValue = store
            },
            onFieldChange = { change ->
                viewModel.onFieldChange(change)
            },
            onImageCaptured = { image ->
                viewModel.onImageCaptured(image)
            },
            onDeleteImageClick = { image ->
                imagePathDeleted = image
            }
        )

        ProvideAppBar(
            showBackNavigation = true,
        )

        storeValue?.let {
            ConfirmDialog(
                title = stringResource(Res.string.tx_global_confirmation),
                description = stringResource(
                    Res.string.tx_global_confirm_save_subtitle
                ),
                primaryButtonText = stringResource(Res.string.tx_global_confirm_save),
                onPrimaryClick = {
                    viewModel.onSaveStore(
                        it
                    )
                    storeValue = null
                },
                secondaryButtonText = stringResource(Res.string.tx_global_cancel),
                onSecondaryClick = {
                    storeValue = null
                },
                onDismiss = {
                    storeValue = null
                }
            )
        }
        imagePathDeleted?.let { imageDelete ->
            ConfirmDialog(
                title = stringResource(Res.string.tx_global_confirmation),
                description = stringResource(
                    Res.string.tx_global_confirm_delete_photo_message
                ),
                primaryButtonText = stringResource(Res.string.tx_global_confirm_delete),
                onPrimaryClick = {
                    viewModel.onFieldChange {
                        copy(images = images.filter { it.filename != imageDelete })
                    }
                    imagePathDeleted = null
                },
                secondaryButtonText = stringResource(Res.string.tx_global_cancel),
                onSecondaryClick = { imagePathDeleted = null },
                onDismiss = {
                    imagePathDeleted = null
                }
            )
        }
    }
}