package com.rgk.qhatu.feature.setting.presentation.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import com.rgk.qhatu.navigation.ProvideFabAction
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_setting_add_new
import qhatuapp.composeapp.generated.resources.tx_setting_cancel
import qhatuapp.composeapp.generated.resources.tx_setting_confirm_delete
import qhatuapp.composeapp.generated.resources.tx_setting_confirm_delete_message
import qhatuapp.composeapp.generated.resources.tx_setting_confirm_edit
import qhatuapp.composeapp.generated.resources.tx_setting_confirm_new
import qhatuapp.composeapp.generated.resources.tx_setting_delete_category
import qhatuapp.composeapp.generated.resources.tx_setting_edit_category
import qhatuapp.composeapp.generated.resources.tx_setting_new_category

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
        var selectedCategoryToNew by remember { mutableStateOf<Boolean>(false) }

        ProvideFabAction {
            if (!isRefreshing) {
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
            isRefreshing = isRefreshing
        )

        selectedCategoryToEdit?.let { category ->
            ContentDialog(
                title = stringResource(Res.string.tx_setting_edit_category),
                content = {
                    CategoryForm(
                        initialName = category.nombre,
                        initialDescription = category.descripcion.orEmpty(),
                        onPrimaryButtonRes = Res.string.tx_setting_confirm_edit,
                        onConfirm = { nombre, descripcion ->
                            viewModel.onItemClick(
                                category.copy(
                                    nombre = nombre,
                                    descripcion = descripcion
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
                title = stringResource(Res.string.tx_setting_new_category),
                content = {
                    CategoryForm(
                        initialName = "",
                        initialDescription = "",
                        onPrimaryButtonRes = Res.string.tx_setting_confirm_new,
                        onConfirm = { nombre, descripcion ->
                            viewModel.onItemClick(
                                Category(
                                    nombre = nombre,
                                    descripcion = descripcion,
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
                title = stringResource(Res.string.tx_setting_delete_category),
                description = stringResource(
                    Res.string.tx_setting_confirm_delete_message,
                    category.nombre
                ),
                primaryButtonText = stringResource(Res.string.tx_setting_confirm_delete),
                onPrimaryClick = {
                    viewModel.onItemClick(category.copy(flag_eliminado = true))
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