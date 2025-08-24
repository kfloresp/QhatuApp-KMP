package com.rgk.qhatu.feature.home.presentation.home

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.toolbar.CartRightSection
import com.rgk.qhatu.navigation.ProvideAppBar
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object HomeDestination

internal fun NavGraphBuilder.homeDestination(
    setLoading: (Boolean) -> Unit,
    navigateToSearch: () -> Unit,
    navigateToSale: () -> Unit,
    navigateToPayment: () -> Unit,
    navigateToCustomer: () -> Unit,
    navigateToProduct: () -> Unit,
    navigateToSetting: () -> Unit,
    navigateToCart: () -> Unit,
) {
    composable<HomeDestination> {
        val viewModel: HomeViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val cartSummary by viewModel.cartSummary.collectAsState()
        ProvideAppBar(
            showAppIcon = true,
            showBackNavigation = false,
            actions = {
                CartRightSection(
                    shoppingCartPrice = cartSummary?.totalSummary.orEmpty(),
                    shoppingCartQuantity = cartSummary?.itemCount ?: 0,
                    onShoppingCartClick = navigateToCart
                )
            }
        )
        HomeScreen(
            uiState = uiState,
            navigateToSearch = navigateToSearch,
            navigateToSale = navigateToSale,
            navigateToPayment = navigateToPayment,
            navigateToCustomer = navigateToCustomer,
            navigateToProduct = navigateToProduct,
            navigateToSetting = navigateToSetting,
            setLoading = setLoading,
        )
    }
}