package com.rgk.qhatu.feature.cart.presentation.cart

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.bottombar.CartSummarySection
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.presentation.product.component.ProductActions
import com.rgk.qhatu.navigation.ProvideAppBar
import com.rgk.qhatu.navigation.ProvideBottomBarApp
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.ic_remove_shopping_cart_24
import qhatuapp.composeapp.generated.resources.tx_cart_confirm_delete_all
import qhatuapp.composeapp.generated.resources.tx_cart_confirm_resume_all
import qhatuapp.composeapp.generated.resources.tx_cart_title
import qhatuapp.composeapp.generated.resources.tx_global_cancel
import qhatuapp.composeapp.generated.resources.tx_global_confirm_continue
import qhatuapp.composeapp.generated.resources.tx_global_confirmation

@Serializable
data object CartDestination

internal fun NavGraphBuilder.cartDestination(
    setLoading: (Boolean) -> Unit,
    onBackPopUp: () -> Unit,
) {
    composable<CartDestination> {
        val viewModel: CartViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val cartSummary by viewModel.cartSummary.collectAsState()
        var showDeleteAllCart by remember { mutableStateOf(false) }
        var showResumeCart by remember { mutableStateOf(false) }
        val operationCart by viewModel.operationCart.collectAsState()

        ProvideAppBar(
            title = "${stringResource(Res.string.tx_cart_title)} ${cartSummary?.itemCountSummary.orEmpty()}",
            actions = {
                if (cartSummary?.hasItems ?: false) {
                    Row {
                        IconButton(onClick = {
                            showResumeCart = true
                        }) {
                            Icon(
                                Icons.Default.ShoppingCartCheckout,
                                contentDescription = null
                            )
                        }
                        IconButton(onClick = {
                            showDeleteAllCart = true
                        }) {
                            Icon(
                                Icons.Default.DeleteSweep,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        )
        ProvideBottomBarApp {
            if (cartSummary?.hasItems ?: false) {
                CartSummarySection(
                    cartSummary?.totalSummary.orEmpty(),
                    onShoppingCartClick = {

                    },
                )
            }
        }

        LaunchedEffect(operationCart) {
            if (operationCart) {
                onBackPopUp()
            }
        }

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
            onRemoveProduct = {
                viewModel.removeItemToCart(it.id)
            }
        )

        if (showDeleteAllCart) {
            ConfirmDialog(
                title = stringResource(Res.string.tx_global_confirmation),
                description = stringResource(
                    Res.string.tx_cart_confirm_delete_all
                ),
                primaryButtonText = stringResource(Res.string.tx_global_confirm_continue),
                onPrimaryClick = {
                    viewModel.onDeleteAllCart()
                    showDeleteAllCart = false
                },
                secondaryButtonText = stringResource(Res.string.tx_global_cancel),
                onSecondaryClick = {
                    showDeleteAllCart = false
                },
                onDismiss = {
                    showDeleteAllCart = false
                }
            )
        }
        if (showResumeCart) {
            ConfirmDialog(
                title = stringResource(Res.string.tx_global_confirmation),
                description = stringResource(
                    Res.string.tx_cart_confirm_resume_all
                ),
                primaryButtonText = stringResource(Res.string.tx_global_confirm_continue),
                onPrimaryClick = {
                    viewModel.onResumeCart()
                    showResumeCart = false
                },
                secondaryButtonText = stringResource(Res.string.tx_global_cancel),
                onSecondaryClick = {
                    showResumeCart = false
                },
                onDismiss = {
                    showResumeCart = false
                }
            )
        }

    }
}