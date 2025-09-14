package com.rgk.qhatu.feature.setting.presentation.unitmeasure

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.button.ButtonFlotableAction
import com.rgk.qhatu.feature.setting.presentation.unitmeasure.component.UnitMeasureFormDialog
import com.rgk.qhatu.navigation.ProvideAppBar
import com.rgk.qhatu.navigation.ProvideFabAction
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_add_new
@Serializable
data object UnitMeasureDestination

internal fun NavGraphBuilder.unitMeasureDestination() {

    composable<UnitMeasureDestination> {
        val viewModel: UnitMeasureViewModel = koinViewModel()
        val uiState by viewModel.listUiState.collectAsState()
        val formState by viewModel.formUiState.collectAsState()

        ProvideFabAction {
            if (uiState is UnitMeasureUiState.Success || uiState is UnitMeasureUiState.Empty) {
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

        UnitMeasureScreen(
            uiState = uiState,
            onQueryChange = viewModel::onQueryChanged,
            onActionClick = { unitMeasure ->
                viewModel.startUpsert(unitMeasure)
            },
        )

        UnitMeasureFormDialog(
            uiState = formState,
            onFieldChange = viewModel::onFieldChange,
            onConfirm = { viewModel.onUpsertCategory(it) },
            onDelete = { viewModel.onUpsertCategory(it.copy(isDeleted = true)) },
            onCancel = { viewModel.cancelForm() },
        )
    }
}