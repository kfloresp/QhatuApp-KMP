package com.rgk.qhatu.feature.search.presentation.search

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.toolbar.CartRightSection
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.presentation.product.component.ProductActions
import com.rgk.qhatu.navigation.ProvideAppBar
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object SearchDestination

internal fun NavGraphBuilder.searchDestination(
    navigateToCart: () -> Unit,
    openScanQR: () -> Unit,
    onProductClick: (String) -> Unit,
) {
    composable<SearchDestination> {
        val viewModel: SearchViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val cartSummary by viewModel.cartSummary.collectAsState()
        ProvideAppBar(
            actions = {
                CartRightSection(
                    shoppingCartPrice = cartSummary?.totalSummary.orEmpty(),
                    shoppingCartQuantity = cartSummary?.itemCount ?: 0,
                    onShoppingCartClick = navigateToCart
                )
            }
        )

        SearchScreen(
            uiState = uiState,
            onQueryChange = viewModel::onQueryChanged,
            onItemClick = {
                onProductClick(it.id)
            },
            productActions = object :
                ProductActions {
                override fun onAddProduct(
                    product: Product,
                    count: Int,
                ) {
                    viewModel.addItemToCart(
                        product.id,
                        count,
                        product.unitPrice.toDouble()
                    )
                }

                override fun onUpdateQuantityProduct(
                    product: Product,
                    count: Int,
                ) {
                    viewModel.updateItemToCart(
                        product.id,
                        count,
                        product.unitPrice.toDouble()
                    )
                }

                override fun onRemoveProduct(product: Product) {
                    viewModel.removeItemToCart(product.id)
                }

            }
        )
    }
}