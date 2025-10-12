package com.rgk.qhatu.feature.sale.presentation.sale
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.toolbar.CartRightSection
import com.rgk.qhatu.navigation.ProvideAppBar
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object SaleDestination

internal fun NavGraphBuilder.saleDestination(
    navigateToCart: () -> Unit,
) {
    composable<SaleDestination> {
        val viewModel: SaleViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val cartSummary by viewModel.cartSummary.collectAsState()
        ProvideAppBar(
            showBackNavigation = false,
            actions = {
                CartRightSection(
                    shoppingCartPrice = cartSummary?.totalSummary.orEmpty(),
                    shoppingCartQuantity = cartSummary?.itemCount ?: 0,
                    onShoppingCartClick = navigateToCart
                )
            }
        )
        SaleScreen(
            uiState,
            onQueryChange = {},
            onItemClick = {},
            onActionClick = {}
        )
    }
}