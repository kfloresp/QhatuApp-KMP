package com.rgk.qhatu.feature.setting.presentation.brand

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.button.ButtonFlotableAction
import com.rgk.qhatu.feature.setting.presentation.brand.component.BrandFormDialog
import com.rgk.qhatu.navigation.ProvideAppBar
import com.rgk.qhatu.navigation.ProvideFabAction
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_add_new

@Serializable
data object BrandDestination

internal fun NavGraphBuilder.brandDestination(
) {
    composable<BrandDestination> {
        val viewModel: BrandViewModel = koinViewModel()
        val uiState by viewModel.listUiState.collectAsState()
        val formState by viewModel.formUiState.collectAsState()

        ProvideFabAction {
            if (uiState is BrandUiState.Success || uiState is BrandUiState.Empty) {
                ButtonFlotableAction(
                    label = stringResource(Res.string.tx_global_add_new)
                ) {
                    viewModel.startUpsert()
                }
            }
        }

        ProvideAppBar(
            showBackNavigation = true,
        )

        BrandScreen(
            uiState = uiState,
            onQueryChange = viewModel::onQueryChanged,
            onActionClick = {
                viewModel.startUpsert(it)
            },
        )
        BrandFormDialog(
            uiState = formState,
            onFieldChange = viewModel::onFieldChange,
            onConfirm = { viewModel.onUpsertBrand(it) },
            onDelete = { viewModel.onUpsertBrand(it.copy(isDeleted = true)) },
            onCancel = { viewModel.cancelForm() },
        )
    }
}