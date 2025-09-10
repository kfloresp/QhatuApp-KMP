package com.rgk.qhatu.feature.setting.presentation.category.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.common.components.dialog.ContentDialog
import com.rgk.qhatu.common.components.loading.LoadingOverlay
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.presentation.category.CategoryFormUiState
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_acept
import qhatuapp.composeapp.generated.resources.tx_error
import qhatuapp.composeapp.generated.resources.tx_global_cancel
import qhatuapp.composeapp.generated.resources.tx_global_category_edit
import qhatuapp.composeapp.generated.resources.tx_global_category_new
import qhatuapp.composeapp.generated.resources.tx_global_confirm_new
import qhatuapp.composeapp.generated.resources.tx_setting_name

@Composable
fun CategoryFormDialog(
    uiState: CategoryFormUiState,
    onFieldChange: (Category.() -> Category) -> Unit,
    onConfirm: (Category) -> Unit,
    onCancel: () -> Unit,
) {
    when (uiState) {
        is CategoryFormUiState.Upsert -> {
            val category = uiState.category
            ContentDialog(
                title = if (uiState.category.id.isEmpty()) {
                    stringResource(Res.string.tx_global_category_new)
                } else {
                    stringResource(Res.string.tx_global_category_edit)
                }, content = {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        CustomTextField(
                            value = category.name, onValueChange = { newValue ->
                                onFieldChange { copy(name = newValue) }
                            }, params = CustomTextFieldParams(
                                label = stringResource(Res.string.tx_setting_name),
                                singleLine = false,
                                maxLength = 50
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        ButtonActions(
                            isEnabled = uiState.isValidForm,
                            primaryButtonText = stringResource(Res.string.tx_global_confirm_new),
                            onPrimaryClick = { onConfirm(category) },
                            secondaryButtonText = stringResource(Res.string.tx_global_cancel),
                            onSecondaryClick = onCancel
                        )
                    }
                }, onDismiss = onCancel
            )
        }

        is CategoryFormUiState.Error -> {
            ConfirmDialog(
                title = stringResource(Res.string.tx_error),
                description = uiState.message,
                primaryButtonText = stringResource(Res.string.tx_acept),
                onPrimaryClick = onCancel,
                onDismiss = onCancel
            )
        }

        CategoryFormUiState.Loading -> {
            LoadingOverlay()
        }

        CategoryFormUiState.Idle -> Unit
    }
}
