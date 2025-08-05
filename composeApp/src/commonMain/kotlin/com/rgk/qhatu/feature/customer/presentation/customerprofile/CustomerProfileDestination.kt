package com.rgk.qhatu.feature.customer.presentation.customerprofile

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.backhandler.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.navigation.ProvideAppBar
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data class CustomerProfileDestination(val idCustomer: String?)

@OptIn(ExperimentalComposeUiApi::class)
internal fun NavGraphBuilder.customerProfileDestination(
    onResumeClick: () -> Unit,
    onBackPopUp: () -> Unit,
    onEditClick: (String) -> Unit,
) {

    composable<CustomerProfileDestination> {
        val viewModel: CustomerProfileViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()

        BackHandler {
            onBackPopUp.invoke()
        }

        ProvideAppBar(
            onBackStack = { onBackPopUp.invoke() }
        )
        CustomerProfileScreen(
            uiState,
            onResumeClick = onResumeClick,
            onEditClick = {
                onEditClick(it.id)
            })
    }
}