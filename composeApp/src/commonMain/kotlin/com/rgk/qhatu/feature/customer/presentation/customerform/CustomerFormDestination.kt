package com.rgk.qhatu.feature.customer.presentation.customerform

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.navigation.ProvideAppBar
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_customer_new_title
import qhatuapp.composeapp.generated.resources.tx_profile_customer_edit

@Serializable
data class CustomerFormDestination(val idCustomer: String)

internal fun NavGraphBuilder.customerFormDestination(
    onBackPopUp: () -> Unit,
) {
    composable<CustomerFormDestination> { destination ->
        val viewModel: CustomerFormViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val isNewCustomer by viewModel.isNewCustomer.collectAsState()

        ProvideAppBar(
            title = if (isNewCustomer) stringResource(Res.string.tx_customer_new_title)
            else stringResource(Res.string.tx_profile_customer_edit),
            onBackStack = { onBackPopUp.invoke() }
        )

        CustomerFormScreen(
            uiState = uiState,
            isNew = isNewCustomer,
            onSaveClick = {
                viewModel.onUpsertLocal(it)
            },
            onDeleteClick = {
                viewModel.onUpsertLocal(it.copy(isDeleted = true))
            },
            onBackPopUp = { onBackPopUp.invoke() }
        )
    }
}