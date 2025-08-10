package com.rgk.qhatu.feature.payment.presentation.paymentform

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.backhandler.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.bottomsheet.CustomBottomSheet
import com.rgk.qhatu.common.components.datepicker.DatePickerComponent
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.common.components.search.SearchContent
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.payment.domain.model.Payment
import com.rgk.qhatu.navigation.ProvideAppBar
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_cancel
import qhatuapp.composeapp.generated.resources.tx_global_confirm_delete
import qhatuapp.composeapp.generated.resources.tx_global_confirm_delete_message
import qhatuapp.composeapp.generated.resources.tx_global_confirm_save
import qhatuapp.composeapp.generated.resources.tx_global_confirm_save_subtitle
import qhatuapp.composeapp.generated.resources.tx_global_confirmation
import qhatuapp.composeapp.generated.resources.tx_payment_new_title
import qhatuapp.composeapp.generated.resources.tx_payment_view_title


@Serializable
data class PaymentFormDestination(val idPayment: String)


@OptIn(ExperimentalComposeUiApi::class)
internal fun NavGraphBuilder.paymentFormDestination(
    onBackPopUp: () -> Unit,
    onDeletePopUp: () -> Unit,
) {
    composable<PaymentFormDestination> { destination ->
        val viewModel: PaymentValidationFormViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val isNewCustomer by viewModel.isNewPayment.collectAsState()
        val formState by viewModel.formState.collectAsState()
        val customerList by viewModel.customerList.collectAsState()
        val methodPaymentList by viewModel.methodPaymentList.collectAsState()

        var selectedPaymentToDelete by remember { mutableStateOf<Payment?>(null) }
        var selectedPaymentToSave by remember { mutableStateOf<Payment?>(null) }
        var selectedCustomerToClick by remember { mutableStateOf(false) }
        var selectedCustomer by remember { mutableStateOf<Customer?>(null) }
        var selectedDatePicker by remember { mutableStateOf(false) }
        var queryCustomer by remember { mutableStateOf("") }

        ProvideAppBar(
            title = if (isNewCustomer) stringResource(Res.string.tx_payment_new_title)
            else stringResource(Res.string.tx_payment_view_title),
            onBackStack = { onBackPopUp.invoke() }
        )
        BackHandler {
            onBackPopUp.invoke()
        }

        if (!isNewCustomer) {
            PaymentViewerScreen(uiState = uiState)
        } else {
            PaymentFormScreen(
                isNew = isNewCustomer,
                uiState = uiState,
                formState = formState,
                onFieldChange = { viewModel.onFieldChange(it) },
                onSaveClick = {
                    selectedPaymentToSave = it
                },
                onDeleteClick = {
                    selectedPaymentToDelete = it
                },
                onCustomerClick = {
                    viewModel.searchCustomer()
                    selectedCustomerToClick = true
                },
                onClearCustomer = {
                    selectedCustomer = null
                },
                selectedCustomer = selectedCustomer,
                methodPayments = methodPaymentList,
                onDatePickerClick = {
                    selectedDatePicker = true
                },
                onBackPopUp = { onBackPopUp.invoke() },
                onDeletePopUp = { onDeletePopUp.invoke() }
            )
        }

        DatePickerComponent(
            showPicker = selectedDatePicker,
            onDateSelected = { date ->
                viewModel.onFieldChange {
                    copy(paymentDate = date)
                }
            },
            initialDate = formState.fields.paymentDate,
            onDismiss = {
                selectedDatePicker = false
            }
        )
        if (selectedCustomerToClick) {
            CustomBottomSheet(isVisible = true, onDismiss = { selectedCustomerToClick = false }) {
                SearchContent(
                    items = customerList,
                    keySelector = { it.id },
                    valueSelector = { it.nameCustomer },
                    query = queryCustomer,
                    onQueryChange = {
                        queryCustomer = it
                        viewModel.onSearchCustomer(it)
                    },
                    onSelectItem = {
                        queryCustomer = ""
                        selectedCustomer = it
                        selectedCustomerToClick = false
                    })
            }
        }

        selectedPaymentToSave?.let {
            ConfirmDialog(
                title = stringResource(Res.string.tx_global_confirmation),
                description = stringResource(Res.string.tx_global_confirm_save_subtitle),
                primaryButtonText = stringResource(Res.string.tx_global_confirm_save),
                onPrimaryClick = {
                    viewModel.onUpsertLocal(it)
                    selectedPaymentToSave = null
                },
                secondaryButtonText = stringResource(Res.string.tx_global_cancel),
                onSecondaryClick = { selectedPaymentToSave = null },
                onDismiss = {
                    selectedPaymentToSave = null
                }
            )
        }

        selectedPaymentToDelete?.let {
            ConfirmDialog(
                title = stringResource(Res.string.tx_global_confirmation),
                description = stringResource(
                    Res.string.tx_global_confirm_delete_message
                ),
                primaryButtonText = stringResource(Res.string.tx_global_confirm_delete),
                onPrimaryClick = {
                    viewModel.onUpsertLocal(it.copy(isDeleted = true))
                    selectedPaymentToDelete = null
                },
                secondaryButtonText = stringResource(Res.string.tx_global_cancel),
                onSecondaryClick = { selectedPaymentToDelete = null },
                onDismiss = {
                    selectedPaymentToDelete = null
                }
            )
        }
    }
}