package com.rgk.qhatu.feature.setting.presentation.unitmeasure

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
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import com.rgk.qhatu.feature.setting.presentation.brand.component.BrandForm
import com.rgk.qhatu.feature.setting.presentation.unitmeasure.component.UnitMeasureForm
import com.rgk.qhatu.navigation.ProvideAppBarActions
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
import qhatuapp.composeapp.generated.resources.tx_setting_unit_measure_delete
import qhatuapp.composeapp.generated.resources.tx_setting_unit_measure_edit
import qhatuapp.composeapp.generated.resources.tx_setting_unit_measure_new

@Serializable
data object UnitMeasureDestination

internal fun NavGraphBuilder.unitMeasureDestination(

) {

    composable<UnitMeasureDestination> {
        val viewModel: UnitMeasureViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()

        val isRefreshing by viewModel.isRefreshing.collectAsState()
        var selectedUnitMeasureToEdit by remember { mutableStateOf<UnitMeasure?>(null) }
        var selectedUnitMeasureToDelete by remember { mutableStateOf<UnitMeasure?>(null) }
        var selectedUnitMeasureToNew by remember { mutableStateOf(false) }

        ProvideAppBarActions {
            Button(onClick = {
                viewModel.fetchRemote()
            }) {
                Text("SYNC")
            }
        }

        ProvideFabAction {
            if (!isRefreshing && uiState is UnitMeasureUiState.Success || uiState is UnitMeasureUiState.Empty) {
                ButtonFlotableAction(
                    label = stringResource(Res.string.tx_setting_add_new)
                ) {
                    selectedUnitMeasureToNew = true
                }
            }
        }

        UnitMeasureScreen(
            uiState = uiState,
            onQueryChange = viewModel::onQueryChanged,
            onItemClick = {
                selectedUnitMeasureToEdit = it
            },
            onActionClick = {
                selectedUnitMeasureToDelete = it
            },
            onPullRefresh = viewModel::onPullRefresh,
            isRefreshing = isRefreshing,
        )

        selectedUnitMeasureToEdit?.let { unitMeasure ->
            ContentDialog(
                title = stringResource(Res.string.tx_setting_unit_measure_edit),
                content = {
                    UnitMeasureForm(
                        initialName = unitMeasure.name,
                        initialDescription = unitMeasure.description.orEmpty(),
                        initialAbbreviation = unitMeasure.abbreviation.orEmpty(),
                        onPrimaryButtonRes = Res.string.tx_setting_confirm_edit,
                        onConfirm = { name, description, abbreviation ->
                            viewModel.onItemClick(
                                unitMeasure.copy(
                                    name = name,
                                    description = description,
                                    abbreviation = abbreviation,
                                )
                            )
                            selectedUnitMeasureToEdit = null
                        },
                        onCancel = {
                            selectedUnitMeasureToEdit = null
                        }
                    )
                },
                onDismiss = {
                    selectedUnitMeasureToEdit = null
                }
            )
        }

        if (selectedUnitMeasureToNew) {
            ContentDialog(
                title = stringResource(Res.string.tx_setting_unit_measure_new),
                content = {
                    UnitMeasureForm(
                        initialName = "",
                        initialDescription = "",
                        initialAbbreviation = "",
                        onPrimaryButtonRes = Res.string.tx_setting_confirm_new,
                        onConfirm = { name, description, abbreviation ->
                            viewModel.onItemClick(
                                UnitMeasure(
                                    name = name,
                                    description = description,
                                    abbreviation = abbreviation,
                                )
                            )
                            selectedUnitMeasureToNew = false
                        },
                        onCancel = {
                            selectedUnitMeasureToNew = false
                        }
                    )
                },
                onDismiss = {
                    selectedUnitMeasureToNew = false
                }
            )
        }

        selectedUnitMeasureToDelete?.let { unitMeasure ->
            ConfirmDialog(
                title = stringResource(Res.string.tx_setting_unit_measure_delete),
                description = stringResource(
                    Res.string.tx_setting_confirm_delete_message,
                    unitMeasure.name
                ),
                primaryButtonText = stringResource(Res.string.tx_setting_confirm_delete),
                onPrimaryClick = {
                    viewModel.onItemClick(unitMeasure.copy(isDeleted = true))
                    selectedUnitMeasureToDelete = null
                },
                secondaryButtonText = stringResource(Res.string.tx_setting_cancel),
                onSecondaryClick = {
                    selectedUnitMeasureToDelete = null
                },
                onDismiss = {
                    selectedUnitMeasureToDelete = null
                }
            )
        }
    }
}