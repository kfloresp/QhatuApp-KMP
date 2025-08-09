package com.rgk.qhatu.feature.payment.presentation.paymentform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.chip.ChipGroup
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.loading.LoadingSection
import com.rgk.qhatu.common.components.textfield.ClickableTextField
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.payment.domain.model.Payment
import com.rgk.qhatu.feature.setting.domain.model.Configuration
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
    onCustomerClick: () -> Unit,
    onClearCustomer: () -> Unit,
    selectedCustomer: Customer? = null,
    onBackPopUp: () -> Unit,
    onDeletePopUp: () -> Unit,
) {
    val fields = formState.fields
    val methodPayments: List<Configuration> = listOf(
        Configuration(id = "1", name = "Yape"),
        Configuration(id = "2", name = "Efectivo"),
        Configuration(id = "3", name = "Crédito")
    )

    when (uiState) {
        is PaymentFormUiState.Error -> {
            ErrorSection(uiState.message)
        }

        PaymentFormUiState.Loading -> {
            LoadingSection()
        }

        is PaymentFormUiState.Success -> {
            val selectedName = selectedCustomer?.nameCustomer.orEmpty()
            val pendingAmount = selectedCustomer?.pendingCustomer
            val hasPendingAmount = selectedCustomer?.havePendingAmount == true
            val selectedId = selectedCustomer?.id.orEmpty()

            if (selectedId.isNotEmpty()) {
                onFieldChange {
                    copy(clientId = selectedId)
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(12.dp)
                    .verticalScroll(rememberScrollState()).imePadding(),
            ) {
                Column {
                    Text(
                        text = "Cliente",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    ClickableTextField(
                        selectedText = selectedName,
                        "Seleccionar cliente",
                        onClick = {
                            onCustomerClick.invoke()
                        },
                        onClear = {
                            onClearCustomer.invoke()
                        }
                    )
                    if (hasPendingAmount && pendingAmount != null) {
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = "Saldo actual: $pendingAmount",
                            style = MaterialTheme.typography.titleSmall,
                            textAlign = TextAlign.Center
                        )
                    }
                    Text(
                        text = "Detalles del pago",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = "Fecha: 15 de Agosto, 2025",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(12.dp))
                    CustomTextField(
                        value = fields.amountPaid.toString(), onValueChange = {
                            onFieldChange {
                                copy(amountPaid = it.toDouble())
                            }
                        }, params = CustomTextFieldParams(
                            label = "Monto de pago",
                            singleLine = true,
                            maxLength = 50,
                            leadingIcon = {
                                Text("S/.")
                            }
                        )
                    )
                    Spacer(Modifier.height(12.dp))
                    CustomTextField(
                        value = fields.comments, onValueChange = {
                            onFieldChange {
                                copy(comments = it)
                            }
                        }, params = CustomTextFieldParams(
                            label = "Comentario (Opcional)", singleLine = true, maxLength = 50
                        )
                    )
                    Text(
                        text = "Método de pago",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    ChipGroup(
                        items = methodPayments,
                        keySelector = { it.id },
                        valueSelector = { it.name },
                        selectedKey = fields.paymentMethodId,
                        onChipClick = { methodPayment ->
                            onFieldChange {
                                copy(paymentMethodId = methodPayment.id)
                            }
                        },
                        errorText = ""
                    )
                }

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
                        onSecondaryClick = { onDeleteClick(fields.copy(isDeleted = true)) })
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