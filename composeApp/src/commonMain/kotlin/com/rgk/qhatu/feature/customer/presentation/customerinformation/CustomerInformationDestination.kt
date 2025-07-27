package com.rgk.qhatu.feature.customer.presentation.customerinformation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.navigation.ProvideAppBarTitle
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_customer_info_title
import qhatuapp.composeapp.generated.resources.tx_customer_new_title

@Serializable
data class CustomerInformationDestination(val idCustomer: String?)

internal fun NavGraphBuilder.customerInformationDestination(
) {
    composable<CustomerInformationDestination> {
        val viewModel: CustomerInformationViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val isNewCustomer by viewModel.isNewCustomer.collectAsState()

        ProvideAppBarTitle(
            if (isNewCustomer) stringResource(Res.string.tx_customer_new_title) else stringResource(
                Res.string.tx_customer_info_title
            )
        )

        CustomerInformationScreen(uiState)
    }
}