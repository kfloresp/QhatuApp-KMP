package com.rgk.qhatu.feature.customer.presentation.customerprofile

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data class CustomerProfileDestination(val idCustomer: String?)

internal fun NavGraphBuilder.customerProfileDestination(
    onResumeClick: () -> Unit,
    onEditClick: (String) -> Unit,
) {
    composable<CustomerProfileDestination> {
        val viewModel: CustomerProfileViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()

        CustomerProfileScreen(
            uiState,
            onResumeClick = onResumeClick,
            onEditClick = {
                onEditClick(it.id)
            })
    }
}