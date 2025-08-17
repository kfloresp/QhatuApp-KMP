package com.rgk.qhatu.feature.product.presentation.product

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
data object ProductDestination

@OptIn(ExperimentalComposeUiApi::class)
internal fun NavGraphBuilder.productDestination(
    onBackPopUp: () -> Unit,
    onProductClick: (String) -> Unit,
    onNewProductClick: () -> Unit,
) {
    composable<ProductDestination> {
        val viewModel: ProductViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val isRefreshing by viewModel.isRefreshing.collectAsState()

        BackHandler {
            onBackPopUp.invoke()
        }
        ProvideAppBar(
            onBackStack = { onBackPopUp.invoke() }
        )
        ProvideFabAction {
            if (!isRefreshing && uiState is ProductUiState.Success || uiState is ProductUiState.Empty) {
                ButtonFlotableAction(
                    label = stringResource(Res.string.tx_global_add_new)
                ) {
                    onNewProductClick()
                }
            }
        }
        ProductScreen(
            uiState = uiState,
            onQueryChange = viewModel::onQueryChanged,
            onItemClick = {
                onProductClick(it.id)
            },
            onPullRefresh = viewModel::onPullRefresh,
            isRefreshing = isRefreshing,
        )
    }
}