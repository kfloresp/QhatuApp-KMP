package com.rgk.qhatu.feature.payment.presentation.payment

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.backhandler.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.button.ButtonFlotableAction
import com.rgk.qhatu.feature.customer.presentation.customer.CustomerUiState
import com.rgk.qhatu.feature.customer.presentation.customer.CustomerViewModel
import com.rgk.qhatu.navigation.ProvideAppBar
import com.rgk.qhatu.navigation.ProvideFabAction
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_add_new

@Serializable
data object PaymentDestination

@OptIn(ExperimentalComposeUiApi::class)
internal fun NavGraphBuilder.paymentDestination(
    onBackPopUp: () -> Unit,
    onCustomerClick: (String) -> Unit,
    onNewPaymentClick: () -> Unit,
) {
    composable<PaymentDestination> {
        val viewModel: PaymentViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val isRefreshing by viewModel.isRefreshing.collectAsState()

        BackHandler {
            onBackPopUp.invoke()
        }
        ProvideAppBar(
            onBackStack = { onBackPopUp.invoke() }
        )
        ProvideFabAction {
            if (!isRefreshing && uiState is PaymentUiState.Success || uiState is PaymentUiState.Empty) {
                ButtonFlotableAction(
                    label = stringResource(Res.string.tx_global_add_new)
                ) {
                    onNewPaymentClick()
                }
            }
        }
        PaymentScreen(
            uiState = uiState,
            onQueryChange = viewModel::onQueryChanged,
            onItemClick = {
                onCustomerClick(it.id)
            },
            onPullRefresh = viewModel::onPullRefresh,
            isRefreshing = isRefreshing,
        )
    }
}