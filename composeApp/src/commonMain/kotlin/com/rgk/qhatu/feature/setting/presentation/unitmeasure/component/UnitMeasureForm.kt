package com.rgk.qhatu.feature.setting.presentation.unitmeasure.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.bottomsheet.FormBottomSheet
import com.rgk.qhatu.common.components.bottomsheet.FormConfig
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import com.rgk.qhatu.feature.setting.presentation.category.CategoryFormUiState
import com.rgk.qhatu.feature.setting.presentation.unitmeasure.UnitMeasureFormUiState
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_accept
import qhatuapp.composeapp.generated.resources.tx_error
import qhatuapp.composeapp.generated.resources.tx_global_cancel
import qhatuapp.composeapp.generated.resources.tx_global_category_edit
import qhatuapp.composeapp.generated.resources.tx_global_category_new
import qhatuapp.composeapp.generated.resources.tx_global_delete_changes
import qhatuapp.composeapp.generated.resources.tx_global_unit_measure_edit
import qhatuapp.composeapp.generated.resources.tx_global_unit_measure_new
import qhatuapp.composeapp.generated.resources.tx_setting_description
import qhatuapp.composeapp.generated.resources.tx_setting_name
import qhatuapp.composeapp.generated.resources.tx_setting_unit_measure_abreviature

@Composable
fun UnitMeasureFormDialog(
    uiState: UnitMeasureFormUiState,
    onFieldChange: (UnitMeasure.() -> UnitMeasure) -> Unit,
    onConfirm: (UnitMeasure) -> Unit,
    onDelete: (UnitMeasure) -> Unit,
    onCancel: () -> Unit,
) {
    when (uiState) {

        is UnitMeasureFormUiState.Upsert -> {
            val unitMeasure = uiState.unitMeasure
            val config = if (unitMeasure.isNew) {
                FormConfig(
                    title = stringResource(Res.string.tx_global_unit_measure_new),
                    secondaryText = stringResource(Res.string.tx_global_cancel),
                    secondaryAction = onCancel
                )
            } else {
                FormConfig(
                    title = stringResource(Res.string.tx_global_unit_measure_edit),
                    secondaryText = stringResource(Res.string.tx_global_delete_changes),
                    secondaryAction = { onDelete(unitMeasure) }
                )
            }

            Box(Modifier.fillMaxSize()) {
                FormBottomSheet(
                    isVisible = true,
                    config = config,
                    isValidForm = uiState.isValidForm,
                    isLoading = uiState.isLoading,
                    onConfirm = { onConfirm(unitMeasure) },
                    onDismiss = onCancel
                ) {
                    CustomTextField(
                        value = unitMeasure.name,
                        onValueChange = { newValue ->
                            onFieldChange { copy(name = newValue) }
                        },
                        params = CustomTextFieldParams(
                            label = stringResource(Res.string.tx_setting_name),
                            singleLine = true,
                            maxLength = 10,
                            enabled = !uiState.isLoading
                        )
                    )
                }
            }
        }

        is UnitMeasureFormUiState.Error -> {
            ConfirmDialog(
                title = stringResource(Res.string.tx_error),
                description = uiState.message,
                primaryButtonText = stringResource(Res.string.tx_accept),
                onPrimaryClick = onCancel,
                onDismiss = onCancel
            )
        }

        UnitMeasureFormUiState.Idle -> Unit
    }
}
