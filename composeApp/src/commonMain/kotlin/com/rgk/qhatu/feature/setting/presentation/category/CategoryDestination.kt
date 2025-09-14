package com.rgk.qhatu.feature.setting.presentation.category

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.button.ButtonFlotableAction
import com.rgk.qhatu.feature.setting.presentation.category.component.CategoryFormDialog
import com.rgk.qhatu.navigation.ProvideAppBar
import com.rgk.qhatu.navigation.ProvideFabAction
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_add_new

@Serializable
data object CategoryDestination

internal fun NavGraphBuilder.categoryDestination() {
    composable<CategoryDestination> {
        val viewModel: CategoryViewModel = koinViewModel()
        val uiState by viewModel.listUiState.collectAsState()
        val formState by viewModel.formUiState.collectAsState()

        ProvideFabAction {
            if (uiState is CategoryUiState.Success || uiState is CategoryUiState.Empty) {
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

        CategoryScreen(
            uiState = uiState,
            onQueryChange = viewModel::onQueryChanged,
            onActionClick = { category ->
                viewModel.startUpsert(category)
            },
        )

        CategoryFormDialog(
            uiState = formState,
            onFieldChange = viewModel::onFieldChange,
            onConfirm = { viewModel.onUpsertCategory(it) },
            onDelete = { viewModel.onUpsertCategory(it.copy(isDeleted = true)) },
            onCancel = { viewModel.cancelForm() },
        )
    }
}