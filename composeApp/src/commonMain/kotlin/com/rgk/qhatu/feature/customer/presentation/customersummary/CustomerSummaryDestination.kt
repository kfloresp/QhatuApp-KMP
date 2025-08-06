package com.rgk.qhatu.feature.customer.presentation.customersummary

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data class CustomerSummaryDestination(val idCustomer: String)

internal fun NavGraphBuilder.customerSummaryDestination(
) {
    composable<CustomerSummaryDestination> {
        val viewModel: CustomerSummaryViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val customerProfileUiState by viewModel.customerProfileUiState.collectAsState()

        CustomerSummaryScreen(customerProfileUiState = customerProfileUiState ,uiState = uiState, onBackPopUp = {})
    }
}