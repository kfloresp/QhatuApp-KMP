package com.rgk.qhatu.feature.cart.presentation.cart

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.presentation.product.component.ProductActions
import com.rgk.qhatu.navigation.ProvideAppBar
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.ic_remove_shopping_cart_24

@Serializable
data object CartDestination

internal fun NavGraphBuilder.cartDestination(
    setLoading: (Boolean) -> Unit,
) {
    composable<CartDestination> {
        val viewModel: CartViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val cartSummary by viewModel.cartSummary.collectAsState()

        ProvideAppBar(
            title = "Carrito ${cartSummary?.itemCountSummary}",
            actions = {
                Row {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            Icons.Default.ShoppingCartCheckout,
                            contentDescription = "Pausar"
                        )
                    }
                    IconButton(onClick = {

                    }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_remove_shopping_cart_24),
                            contentDescription = "Remove"
                        )
                    }
                }
            }
        )
        CartScreen(
            uiState = uiState,
            setLoading = setLoading,
            productActions = object : ProductActions {
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
            },
        )
    }
}