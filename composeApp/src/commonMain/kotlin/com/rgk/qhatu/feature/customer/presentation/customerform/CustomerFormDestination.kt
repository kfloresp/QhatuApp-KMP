package com.rgk.qhatu.feature.customer.presentation.customerform

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.backhandler.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.model.CustomerWithDetails
import com.rgk.qhatu.feature.customer.domain.model.DocumentType
import com.rgk.qhatu.feature.customer.domain.model.updateCustomer
import com.rgk.qhatu.navigation.ProvideAppBar
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_customer_new_title
import qhatuapp.composeapp.generated.resources.tx_global_cancel
import qhatuapp.composeapp.generated.resources.tx_global_confirm_delete
import qhatuapp.composeapp.generated.resources.tx_global_confirm_delete_message
import qhatuapp.composeapp.generated.resources.tx_global_confirm_save
import qhatuapp.composeapp.generated.resources.tx_global_confirm_save_subtitle
import qhatuapp.composeapp.generated.resources.tx_global_confirmation
import qhatuapp.composeapp.generated.resources.tx_profile_customer_edit

@Serializable
data class CustomerFormDestination(val customerId: String, val documentType: String)

@OptIn(ExperimentalComposeUiApi::class)
internal fun NavGraphBuilder.customerFormDestination(
    onBackPopUp: (String, DocumentType) -> Unit,
) {
    composable<CustomerFormDestination> { destination ->
        val viewModel: CustomerFormViewModel = koinViewModel()
        val isNewCustomer by viewModel.isNewCustomer.collectAsState()
        val formUiState by viewModel.formUiState.collectAsState()

        var selectedCustomerToDelete by remember { mutableStateOf<CustomerWithDetails?>(null) }
        var selectedCustomerToSave by remember { mutableStateOf<CustomerWithDetails?>(null) }

        ProvideAppBar(
            title = if (isNewCustomer) stringResource(Res.string.tx_customer_new_title)
            else stringResource(Res.string.tx_profile_customer_edit),
            onBackStack = { onBackPopUp.invoke(viewModel.customerId, viewModel.documentType) }
        )

        BackHandler {
            onBackPopUp.invoke(viewModel.customerId, viewModel.documentType)
        }

        CustomerFormScreen(
            isNew = isNewCustomer,
            uiState = formUiState,
            onFieldChange = { viewModel.onFieldChange(it) },
            onSaveClick = {
                selectedCustomerToSave = it
            },
            onDeleteClick = {
                selectedCustomerToDelete = it
            },
            onBackPopUp = {
                onBackPopUp(viewModel.customerId, viewModel.documentType)
            }
        )

        selectedCustomerToSave?.let {
            ConfirmDialog(
                title = stringResource(Res.string.tx_global_confirmation),
                description = stringResource(Res.string.tx_global_confirm_save_subtitle),
                primaryButtonText = stringResource(Res.string.tx_global_confirm_save),
                onPrimaryClick = {
                    viewModel.onUpsertLocal(it)
                    selectedCustomerToSave = null
                },
                secondaryButtonText = stringResource(Res.string.tx_global_cancel),
                onSecondaryClick = { selectedCustomerToSave = null },
                onDismiss = {
                    selectedCustomerToSave = null
                }
            )
        }

        selectedCustomerToDelete?.let {
            ConfirmDialog(
                title = stringResource(Res.string.tx_global_confirmation),
                description = stringResource(
                    Res.string.tx_global_confirm_delete_message
                ),
                primaryButtonText = stringResource(Res.string.tx_global_confirm_delete),
                onPrimaryClick = {
                    val customer = when (it) {
                        is CustomerWithDetails.CompanyWithCustomer -> {
                            it.updateCustomer { it.copy(isDeleted = true) }
                        }

                        is CustomerWithDetails.PersonWithCustomer -> {
                            it.updateCustomer { it.copy(isDeleted = true) }
                        }
                    }
                    viewModel.onUpsertLocal(customer)
                    selectedCustomerToDelete = null
                },
                secondaryButtonText = stringResource(Res.string.tx_global_cancel),
                onSecondaryClick = { selectedCustomerToDelete = null },
                onDismiss = {
                    selectedCustomerToDelete = null
                }
            )
        }
    }
}