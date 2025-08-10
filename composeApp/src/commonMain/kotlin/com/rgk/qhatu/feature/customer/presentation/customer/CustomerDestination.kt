package com.rgk.qhatu.feature.customer.presentation.customer

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.backhandler.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.button.ButtonFlotableAction
import com.rgk.qhatu.navigation.ProvideAppBar
import com.rgk.qhatu.navigation.ProvideFabAction
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_add_new

@Serializable
data object CustomerDestination

@OptIn(ExperimentalComposeUiApi::class)
internal fun NavGraphBuilder.customerDestination(
    onCustomerClick: (String) -> Unit,
    onNewCustomerClick: () -> Unit,
    onBackPopUp:() -> Unit,
) {
    composable<CustomerDestination> {
        val viewModel: CustomerViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val isRefreshing by viewModel.isRefreshing.collectAsState()

        BackHandler {
            onBackPopUp.invoke()
        }
        ProvideAppBar(
            onBackStack = { onBackPopUp.invoke() }
        )
        ProvideFabAction {
            if (!isRefreshing && uiState is CustomerUiState.Success || uiState is CustomerUiState.Empty) {
                ButtonFlotableAction(
                    label = stringResource(Res.string.tx_global_add_new)
                ) {
                    onNewCustomerClick()
                }
            }
        }

        CustomerScreen(
            uiState = uiState,
            onQueryChange = viewModel::onQueryChanged,
            onItemClick = {
                onCustomerClick(it.id)
            },
            onActionClick = {
                //Validar que tenga número de celular
                //Crear funcion expect/actual
            },
            onPullRefresh = viewModel::onPullRefresh,
            isRefreshing = isRefreshing,
        )
    }
}