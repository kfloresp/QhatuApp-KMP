package com.rgk.qhatu.feature.setting.presentation.brand

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.common.components.dialog.ContentDialog
import com.rgk.qhatu.feature.setting.domain.model.Brand
import com.rgk.qhatu.feature.setting.presentation.brand.component.BrandForm
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_setting_brand_delete
import qhatuapp.composeapp.generated.resources.tx_setting_brand_edit
import qhatuapp.composeapp.generated.resources.tx_setting_brand_new
import qhatuapp.composeapp.generated.resources.tx_setting_cancel
import qhatuapp.composeapp.generated.resources.tx_setting_confirm_delete
import qhatuapp.composeapp.generated.resources.tx_setting_confirm_delete_message
import qhatuapp.composeapp.generated.resources.tx_setting_confirm_edit
import qhatuapp.composeapp.generated.resources.tx_setting_confirm_new

@Serializable
data object BrandDestination

internal fun NavGraphBuilder.brandDestination(
) {
    composable<BrandDestination> {
        val viewModel: BrandViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val isRefreshing by viewModel.isRefreshing.collectAsState()
        var selectedBrandToEdit by remember { mutableStateOf<Brand?>(null) }
        var selectedBrandToDelete by remember { mutableStateOf<Brand?>(null) }
        var selectedBrandToNew by remember { mutableStateOf(false) }

        BrandScreen(
            uiState = uiState,
            onQueryChange = viewModel::onQueryChanged,
            onItemClick = {
                selectedBrandToEdit = it
            },
            onActionClick = {
                selectedBrandToDelete = it
            },
            onPullRefresh = viewModel::onPullRefresh,
            isRefreshing = isRefreshing,
        )

        selectedBrandToEdit?.let { brand ->
            ContentDialog(
                title = stringResource(Res.string.tx_setting_brand_edit),
                content = {
                    BrandForm(
                        initialName = brand.name,
                        initialDescription = brand.description.orEmpty(),
                        onPrimaryButtonRes = Res.string.tx_setting_confirm_edit,
                        onConfirm = { nameUnitMeasure, descriptionUnitMeasure ->
                            viewModel.onItemClick(
                                brand.copy(
                                    name = nameUnitMeasure,
                                    description = descriptionUnitMeasure
                                )
                            )
                            selectedBrandToEdit = null
                        },
                        onCancel = {
                            selectedBrandToEdit = null
                        }
                    )
                },
                onDismiss = {
                    selectedBrandToEdit = null
                }
            )
        }

        if (selectedBrandToNew) {
            ContentDialog(
                title = stringResource(Res.string.tx_setting_brand_new),
                content = {
                    BrandForm(
                        initialName = "",
                        initialDescription = "",
                        onPrimaryButtonRes = Res.string.tx_setting_confirm_new,
                        onConfirm = { nameBrand, descriptionBrand ->
                            viewModel.onItemClick(
                                Brand(
                                    name = nameBrand,
                                    description = descriptionBrand,
                                )
                            )
                            selectedBrandToNew = false
                        },
                        onCancel = {
                            selectedBrandToNew = false
                        }
                    )
                },
                onDismiss = {
                    selectedBrandToNew = false
                }
            )
        }

        selectedBrandToDelete?.let { brand ->
            ConfirmDialog(
                title = stringResource(Res.string.tx_setting_brand_delete),
                description = stringResource(
                    Res.string.tx_setting_confirm_delete_message,
                    brand.name
                ),
                primaryButtonText = stringResource(Res.string.tx_setting_confirm_delete),
                onPrimaryClick = {
                    viewModel.onItemClick(brand.copy(isDeleted = true))
                    selectedBrandToDelete = null
                },
                secondaryButtonText = stringResource(Res.string.tx_setting_cancel),
                onSecondaryClick = {
                    selectedBrandToDelete = null
                },
                onDismiss = {
                    selectedBrandToDelete = null
                }
            )
        }
    }
}