package com.rgk.qhatu.feature.setting.presentation.category

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.button.ButtonFlotableAction
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.common.components.dialog.ContentDialog
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.presentation.category.component.CategoryForm
import com.rgk.qhatu.navigation.ProvideAppBarActions
import com.rgk.qhatu.navigation.ProvideFabAction
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_setting_add_new
import qhatuapp.composeapp.generated.resources.tx_setting_cancel
import qhatuapp.composeapp.generated.resources.tx_setting_category_delete
import qhatuapp.composeapp.generated.resources.tx_setting_category_edit
import qhatuapp.composeapp.generated.resources.tx_setting_category_new
import qhatuapp.composeapp.generated.resources.tx_setting_confirm_delete
import qhatuapp.composeapp.generated.resources.tx_setting_confirm_delete_message
import qhatuapp.composeapp.generated.resources.tx_setting_confirm_edit
import qhatuapp.composeapp.generated.resources.tx_setting_confirm_new

@Serializable
data object CategoryDestination

internal fun NavGraphBuilder.categoryDestination(
) {
    composable<CategoryDestination> {
        val viewModel: CategoryViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val isRefreshing by viewModel.isRefreshing.collectAsState()
        var selectedCategoryToEdit by remember { mutableStateOf<Category?>(null) }
        var selectedCategoryToDelete by remember { mutableStateOf<Category?>(null) }
        var selectedCategoryToNew by remember { mutableStateOf(false) }

        ProvideFabAction {
            if (!isRefreshing && uiState is CategoryUiState.Success || uiState is CategoryUiState.Empty) {
                ButtonFlotableAction(
                    label = stringResource(Res.string.tx_setting_add_new)
                ) {
                    selectedCategoryToNew = true
                }
            }
        }

        CategoryScreen(
            uiState = uiState,
            onQueryChange = viewModel::onQueryChanged,
            onItemClick = {
                selectedCategoryToEdit = it
            },
            onActionClick = {
                selectedCategoryToDelete = it
            },
            onPullRefresh = viewModel::onPullRefresh,
            isRefreshing = isRefreshing,
        )

        selectedCategoryToEdit?.let { category ->
            ContentDialog(
                title = stringResource(Res.string.tx_setting_category_edit),
                content = {
                    CategoryForm(
                        initialName = category.name,
                        initialDescription = category.description.orEmpty(),
                        onPrimaryButtonRes = Res.string.tx_setting_confirm_edit,
                        onConfirm = { name, description ->
                            viewModel.onItemClick(
                                category.copy(
                                    name = name,
                                    description = description
                                )
                            )
                            selectedCategoryToEdit = null
                        },
                        onCancel = {
                            selectedCategoryToEdit = null
                        }
                    )
                },
                onDismiss = {
                    selectedCategoryToEdit = null
                }
            )
        }

        if (selectedCategoryToNew) {
            ContentDialog(
                title = stringResource(Res.string.tx_setting_category_new),
                content = {
                    CategoryForm(
                        initialName = "",
                        initialDescription = "",
                        onPrimaryButtonRes = Res.string.tx_setting_confirm_new,
                        onConfirm = { name, description ->
                            viewModel.onItemClick(
                                Category(
                                    name = name,
                                    description = description,
                                )
                            )
                            selectedCategoryToNew = false
                        },
                        onCancel = {
                            selectedCategoryToNew = false
                        }
                    )
                },
                onDismiss = {
                    selectedCategoryToNew = false
                }
            )
        }

        selectedCategoryToDelete?.let { category ->
            ConfirmDialog(
                title = stringResource(Res.string.tx_setting_category_delete),
                description = stringResource(
                    Res.string.tx_setting_confirm_delete_message,
                    category.name
                ),
                primaryButtonText = stringResource(Res.string.tx_setting_confirm_delete),
                onPrimaryClick = {
                    viewModel.onItemClick(category.copy(isDeleted = true))
                    selectedCategoryToDelete = null
                },
                secondaryButtonText = stringResource(Res.string.tx_setting_cancel),
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