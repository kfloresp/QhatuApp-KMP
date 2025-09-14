package com.rgk.qhatu.feature.setting.presentation.category.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rgk.qhatu.common.components.bottomsheet.FormBottomSheet
import com.rgk.qhatu.common.components.bottomsheet.FormConfig
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.presentation.category.CategoryFormUiState
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_accept
import qhatuapp.composeapp.generated.resources.tx_error
import qhatuapp.composeapp.generated.resources.tx_global_cancel
import qhatuapp.composeapp.generated.resources.tx_global_category_edit
import qhatuapp.composeapp.generated.resources.tx_global_category_new
import qhatuapp.composeapp.generated.resources.tx_global_delete_changes
import qhatuapp.composeapp.generated.resources.tx_setting_name

@Composable
fun CategoryFormDialog(
    uiState: CategoryFormUiState,
    onFieldChange: (Category.() -> Category) -> Unit,
    onConfirm: (Category) -> Unit,
    onDelete: (Category) -> Unit,
    onCancel: () -> Unit,
) {
    when (uiState) {

        is CategoryFormUiState.Upsert -> {
            val category = uiState.category
            val config = if (category.isNew) {
                FormConfig(
                    title = stringResource(Res.string.tx_global_category_new),
                    secondaryText = stringResource(Res.string.tx_global_cancel),
                    secondaryAction = onCancel
                )
            } else {
                FormConfig(
                    title = stringResource(Res.string.tx_global_category_edit),
                    secondaryText = stringResource(Res.string.tx_global_delete_changes),
                    secondaryAction = { onDelete(category) }
                )
            }

            Box(Modifier.fillMaxSize()) {
                FormBottomSheet(
                    isVisible = true,
                    config = config,
                    isValidForm = uiState.isValidForm,
                    isLoading = uiState.isLoading,
                    onConfirm = { onConfirm(category) },
                    onDismiss = onCancel
                ) {
                    CustomTextField(
                        value = category.name,
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

        is CategoryFormUiState.Error -> {
            ConfirmDialog(
                title = stringResource(Res.string.tx_error),
                description = uiState.message,
                primaryButtonText = stringResource(Res.string.tx_accept),
                onPrimaryClick = onCancel,
                onDismiss = onCancel
            )
        }

        CategoryFormUiState.Idle -> Unit
    }
}
