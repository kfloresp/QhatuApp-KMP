package com.rgk.qhatu.feature.payment.presentation.paymentform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.loading.LoadingSection
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.payment.domain.model.Payment
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_delete_changes
import qhatuapp.composeapp.generated.resources.tx_global_save_changes

@Composable
fun PaymentFormScreen(
    isNew: Boolean = false,
    uiState: PaymentFormUiState,
    formState: PaymentFormValidationState,
    onFieldChange: (Payment.() -> Payment) -> Unit,
    onSaveClick: (Payment) -> Unit,
    onDeleteClick: (Payment) -> Unit,
    onBackPopUp: () -> Unit,
    onDeletePopUp: () -> Unit,
){
    val fields = formState.fields


    when (uiState) {
        is PaymentFormUiState.Error -> {
            ErrorSection(uiState.message)
        }

        PaymentFormUiState.Loading -> {
            LoadingSection()
        }

        is PaymentFormUiState.Success -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth().padding(12.dp)
                    .verticalScroll(rememberScrollState())
                    .imePadding(),
            ) {
                CustomTextField(
                    value = fields.clientId,
                    onValueChange = {
                        onFieldChange {
                            copy(clientId = it)
                        }
                    },
                    params = CustomTextFieldParams(
                        label = "Cliente",
                        singleLine = true,
                        maxLength = 50
                    )
                )
                CustomTextField(
                    value = fields.paymentMethodId,
                    onValueChange = {
                        onFieldChange {
                            copy(paymentMethodId = it)
                        }
                    },
                    params = CustomTextFieldParams(
                        label = "Método de pago",
                        singleLine = true,
                        maxLength = 50
                    )
                )

                if (isNew) {
                    ButtonActions(
                        modifier = Modifier.padding(12.dp),
                        primaryButtonText = stringResource(Res.string.tx_global_save_changes),
                        isEnabled = formState.isValid,
                        onPrimaryClick = {
                            onSaveClick(fields)
                        },
                    )
                } else {
                    ButtonActions(
                        modifier = Modifier.padding(12.dp),
                        primaryButtonText = stringResource(Res.string.tx_global_save_changes),
                        isEnabled = formState.isValid,
                        onPrimaryClick = {
                            onSaveClick(fields)
                        },
                        secondaryButtonText = stringResource(Res.string.tx_global_delete_changes),
                        onSecondaryClick = { onDeleteClick(fields.copy(isDeleted = true)) }
                    )
                }
            }
        }

        is PaymentFormUiState.SuccessUpsert -> {
            val isDeleted = uiState.isDeleted
            if (isDeleted) {
                onDeletePopUp()
            } else {
                onBackPopUp()
            }
        }

    }
}