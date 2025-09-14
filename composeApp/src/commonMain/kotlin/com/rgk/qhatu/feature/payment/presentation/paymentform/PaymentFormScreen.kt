package com.rgk.qhatu.feature.payment.presentation.paymentform

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.chip.ChipGroup
import com.rgk.qhatu.common.components.datepicker.toFormattedDate
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.loading.LoadingSection
import com.rgk.qhatu.common.components.textfield.ClickableTextField
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.payment.domain.model.Payment
import com.rgk.qhatu.feature.sale.domain.model.SalePaymentMethod
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_delete_changes
import qhatuapp.composeapp.generated.resources.tx_global_save_changes
import qhatuapp.composeapp.generated.resources.tx_payment_currency_symbol
import qhatuapp.composeapp.generated.resources.tx_payment_current_balance
import qhatuapp.composeapp.generated.resources.tx_payment_date
import qhatuapp.composeapp.generated.resources.tx_payment_method
import qhatuapp.composeapp.generated.resources.tx_payment_operation_number_optional
import qhatuapp.composeapp.generated.resources.tx_payment_payment_details
import qhatuapp.composeapp.generated.resources.tx_payment_select_customer

const val ID_CONFIG_TYPE_PAYMENT_DEFAULT = "MP0001"
const val PATTERNS = "^\\d{0,9}(\\.\\d{0,2})?$"
@Composable
fun PaymentFormScreen(
    isNew: Boolean = false,
    uiState: PaymentFormUiState,
    formState: PaymentFormValidationState,
    onFieldChange: (Payment.() -> Payment) -> Unit,
    onSaveClick: (Payment) -> Unit,
    onDeleteClick: (Payment) -> Unit,
    onCustomerClick: () -> Unit,
    onDatePickerClick: () -> Unit,
    onClearCustomer: () -> Unit,
    selectedCustomer: Customer? = null,
    methodPayments: List<SalePaymentMethod>,
    onBackPopUp: () -> Unit,
    onDeletePopUp: () -> Unit,
) {
    val fields = formState.fields
    var amountError by remember { mutableStateOf<String?>(null) }
    var amountEnabled by remember { mutableStateOf(false) }

    when (uiState) {
        is PaymentFormUiState.Error -> {
            ErrorSection(uiState.message)
        }

        PaymentFormUiState.Loading -> {
            LoadingSection()
        }

        is PaymentFormUiState.Success -> {
            val selectedName = selectedCustomer?.nameCustomer.orEmpty()
            val pendingCustomer = selectedCustomer?.pendingCustomer
            val hasPendingAmount = selectedCustomer?.havePendingAmount == true
            val selectedId = selectedCustomer?.id.orEmpty()

            LaunchedEffect(selectedId) {
                amountError = null
                amountEnabled = selectedId.isNotEmpty()
                onFieldChange {
                    copy(amountPaid = "")
                }
            }

            if (selectedId.isNotEmpty()) {
                onFieldChange {
                    copy(customerId = selectedId)
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(12.dp)
                    .verticalScroll(rememberScrollState()).imePadding(),
            ) {
                Column {
                    ClickableTextField(
                        selectedText = selectedName,
                        stringResource(Res.string.tx_payment_select_customer),
                        onClick = {
                            onCustomerClick.invoke()
                        },
                        onClear = {
                            onClearCustomer.invoke()
                            amountError = null
                            amountEnabled = false
                            onFieldChange {
                                copy(amountPaid = "")
                            }
                        }
                    )
                    if (hasPendingAmount && pendingCustomer != null) {
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = stringResource(Res.string.tx_payment_current_balance,pendingCustomer),
                            style = MaterialTheme.typography.titleSmall,
                            textAlign = TextAlign.Center
                        )
                    }
                    Text(
                        text = stringResource(Res.string.tx_payment_payment_details),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = stringResource(Res.string.tx_payment_date,fields.paymentDate.toFormattedDate()),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.clickable(enabled = true, onClick = {
                            onDatePickerClick.invoke()
                        })
                    )
                    Spacer(Modifier.height(12.dp))
                    CustomTextField(
                        value = fields.amountPaid, onValueChange = { newValue ->
                            if (newValue.isEmpty()) {
                                amountError = null
                                onFieldChange {
                                    copy(amountPaid = "")
                                }
                                return@CustomTextField
                            }
                            val regex = Regex(PATTERNS)
                            if (newValue.matches(regex)) {
                                onFieldChange {
                                    copy(amountPaid = newValue)
                                }
                            }
                        }, params = CustomTextFieldParams(
                            label = "Precio unitario",
                            error = amountError,
                            singleLine = true,
                            maxLength = 6,
                            enabled = amountEnabled,
                            leadingIcon = {
                                Text(stringResource(Res.string.tx_payment_currency_symbol))
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            )
                        )
                    )
                    Text(
                        text = stringResource(Res.string.tx_payment_method),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    ChipGroup(
                        items = methodPayments,
                        keySelector = { it.value },
                        valueSelector = { it.name },
                        selectedKey = fields.paymentMethodId,
                        onChipClick = { methodPayment ->
                            onFieldChange {
                                copy(paymentMethodId = methodPayment.value)
                            }
                        },
                    )
                    if (fields.paymentMethodId != ID_CONFIG_TYPE_PAYMENT_DEFAULT && fields.paymentMethodId.isNotEmpty()) {
                        CustomTextField(
                            value = fields.numberOperation, onValueChange = {
                                onFieldChange {
                                    copy(numberOperation = it)
                                }
                            }, params = CustomTextFieldParams(
                                label = stringResource(Res.string.tx_payment_operation_number_optional),
                                singleLine = true,
                                maxLength = 10,
                            )
                        )
                    }
                }

                if (isNew) {
                    ButtonActions(
                        modifier = Modifier.padding(12.dp),
                        primaryButtonText = stringResource(Res.string.tx_global_save_changes),
                        isEnabled = formState.isValid && amountError == null,
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