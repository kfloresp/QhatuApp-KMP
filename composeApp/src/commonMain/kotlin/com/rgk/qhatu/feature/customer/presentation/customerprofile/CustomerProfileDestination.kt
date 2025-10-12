package com.rgk.qhatu.feature.customer.presentation.customerprofile

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.backhandler.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.feature.customer.domain.model.DocumentType
import com.rgk.qhatu.navigation.ProvideAppBar
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_profile_customer_profile

@Serializable
data class CustomerProfileDestination(val customerId: String, val documentType: String)

@OptIn(ExperimentalComposeUiApi::class)
internal fun NavGraphBuilder.customerProfileDestination(
    onBackPopUp: () -> Unit,
    onResumeClick: (String) -> Unit,
    onEditClick: (String, DocumentType) -> Unit,
) {

    composable<CustomerProfileDestination> { destination ->
        val viewModel: CustomerProfileViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()

        BackHandler {
            onBackPopUp.invoke()
        }

        ProvideAppBar(
            title = stringResource(Res.string.tx_profile_customer_profile),
            onBackStack = { onBackPopUp.invoke() }
        )

        CustomerProfileScreen(
            uiState,
            onResumeClick = {
                onResumeClick(it)
            },
            onEditClick = { customerId, documentType ->
                onEditClick(customerId, documentType)
            })
    }
}