package com.rgk.qhatu.feature.setting.presentation.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.common.components.dialog.ContentDialog
import com.rgk.qhatu.components.deprecate.CustomTextField
import com.rgk.qhatu.components.deprecate.CustomTextFieldParams
import com.rgk.qhatu.feature.setting.domain.model.Category
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_setting_cancel
import qhatuapp.composeapp.generated.resources.tx_setting_confirm_delete
import qhatuapp.composeapp.generated.resources.tx_setting_confirm_delete_message
import qhatuapp.composeapp.generated.resources.tx_setting_confirm_edit
import qhatuapp.composeapp.generated.resources.tx_setting_delete_category
import qhatuapp.composeapp.generated.resources.tx_setting_description
import qhatuapp.composeapp.generated.resources.tx_setting_edit_category
import qhatuapp.composeapp.generated.resources.tx_setting_name

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
                    var nombre by remember { mutableStateOf(category.nombre) }
                    var descripcion by remember { mutableStateOf(category.descripcion.orEmpty()) }

                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        CustomTextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            params = CustomTextFieldParams(
                                label = stringResource(Res.string.tx_setting_name),
                                singleLine = false,
                                maxLength = 50
                            )
                        )
                        CustomTextField(
                            value = descripcion,
                            onValueChange = { descripcion = it },
                            params = CustomTextFieldParams(
                                label = stringResource(Res.string.tx_setting_description),
                                singleLine = false,
                                maxLength = 50
                            )
                        )
                    }
                    ButtonActions(
                        primaryButtonText = stringResource(Res.string.tx_setting_confirm_edit),
                        onPrimaryClick = {
                            viewModel.onItemClick(
                                category.copy(
                                    nombre = nombre,
                                    descripcion = descripcion
                                )
                            )
                            selectedCategoryToEdit = null
                        },
                        secondaryButtonText = stringResource(Res.string.tx_setting_cancel),
                        onSecondaryClick = {
                            selectedCategoryToEdit = null
                        })
                },
                onDismiss = {
                    selectedCategoryToEdit = null
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