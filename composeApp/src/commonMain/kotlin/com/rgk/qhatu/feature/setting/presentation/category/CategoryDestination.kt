package com.rgk.qhatu.feature.setting.presentation.category

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.button.ButtonFlotableAction
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.presentation.category.component.CategoryFormDialog
import com.rgk.qhatu.navigation.ProvideAppBar
import com.rgk.qhatu.navigation.ProvideFabAction
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_add_new
import qhatuapp.composeapp.generated.resources.tx_global_cancel
import qhatuapp.composeapp.generated.resources.tx_global_confirm_delete
import qhatuapp.composeapp.generated.resources.tx_global_confirm_delete_message
import qhatuapp.composeapp.generated.resources.tx_global_confirmation

@Serializable
data object CategoryDestination

internal fun NavGraphBuilder.categoryDestination() {
    composable<CategoryDestination> {
        val viewModel: CategoryViewModel = koinViewModel()
        val uiState by viewModel.listUiState.collectAsState()
        val formState by viewModel.formUiState.collectAsState()
        val isRefreshing by viewModel.isRefreshing.collectAsState()
        var selectedCategoryToDelete by remember { mutableStateOf<Category?>(null) }

        ProvideFabAction {
            if (!isRefreshing && uiState is CategoryUiState.Success || uiState is CategoryUiState.Empty) {
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
            onItemClick = {
                viewModel.startUpsert(it)
            },
            onActionClick = {
                selectedCategoryToDelete = it
            },
            onPullRefresh = viewModel::onPullRefresh,
            isRefreshing = isRefreshing,
        )

        CategoryFormDialog(
            uiState = formState,
            onFieldChange = viewModel::onFieldChange,
            onConfirm = { viewModel.onUpsertCategory(it) },
            onCancel = { viewModel.cancelForm() }
        )

        selectedCategoryToDelete?.let { category ->
            ConfirmDialog(
                title = stringResource(Res.string.tx_global_confirmation),
                description = stringResource(
                    Res.string.tx_global_confirm_delete_message,
                    category.name
                ),
                primaryButtonText = stringResource(Res.string.tx_global_confirm_delete),
                onPrimaryClick = {
                    viewModel.onUpsertCategory(category.copy(isDeleted = true))
                    selectedCategoryToDelete = null
                },
                secondaryButtonText = stringResource(Res.string.tx_global_cancel),
                onSecondaryClick = {
                    selectedCategoryToDelete = null
                },
                onDismiss = {
                    selectedCategoryToDelete = null
                }
            )
        }

    }
}