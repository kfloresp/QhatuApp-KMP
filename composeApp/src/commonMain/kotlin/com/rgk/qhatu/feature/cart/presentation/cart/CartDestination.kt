package com.rgk.qhatu.feature.cart.presentation.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.bottombar.CartSummarySection
import com.rgk.qhatu.common.components.bottomsheet.CustomBottomSheet
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.feature.cart.presentation.cart.component.CartOption
import com.rgk.qhatu.feature.cart.presentation.cart.component.CartOptions
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.presentation.product.component.ProductActions
import com.rgk.qhatu.navigation.ProvideAppBar
import com.rgk.qhatu.navigation.ProvideBottomBarApp
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_cart_confirm_delete_all
import qhatuapp.composeapp.generated.resources.tx_cart_confirm_delete_product
import qhatuapp.composeapp.generated.resources.tx_cart_confirm_resume_all
import qhatuapp.composeapp.generated.resources.tx_cart_title
import qhatuapp.composeapp.generated.resources.tx_global_cancel
import qhatuapp.composeapp.generated.resources.tx_global_confirm_continue
import qhatuapp.composeapp.generated.resources.tx_global_confirm_delete
import qhatuapp.composeapp.generated.resources.tx_global_confirmation
import qhatuapp.composeapp.generated.resources.tx_global_selection_option

@Serializable
data object CartDestination

internal fun NavGraphBuilder.cartDestination(
    setLoading: (Boolean) -> Unit,
    navigateToCheckout: () -> Unit,
    onBackPopUp: () -> Unit,
) {
    composable<CartDestination> {
        val viewModel: CartViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val cartSummary by viewModel.cartSummary.collectAsState()
        var showDeleteAllCart by remember { mutableStateOf(false) }
        var showResumeCart by remember { mutableStateOf(false) }
        val operationCart by viewModel.operationCart.collectAsState()
        var showOperationCartItem by remember { mutableStateOf<Product?>(null) }
        var showDeleteCartItem by remember { mutableStateOf<Product?>(null) }

        ProvideAppBar(
            title = "${stringResource(Res.string.tx_cart_title)} ${cartSummary?.itemCountSummary.orEmpty()}",
            actions = {
                if (cartSummary?.hasItems ?: false) {
                    Row {
                        IconButton(onClick = {
                            showResumeCart = true
                        }) {
                            Icon(
                                Icons.Default.FavoriteBorder,
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
                    shoppingCartTotal = cartSummary?.totalSummary.orEmpty(),
                    onShoppingCartClick = {
                        navigateToCheckout()
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
                    showDeleteCartItem = product
                }
            },
            onOptionsProduct = {
                showOperationCartItem = it
            }
        )

        showOperationCartItem?.let {
            CustomBottomSheet(
                isVisible = true,
                onDismiss = { showOperationCartItem = null }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = stringResource(Res.string.tx_global_selection_option),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )

                    CartOptions { selected ->
                        when (selected) {
                            CartOption.DELETE_ITEM -> {
                                showOperationCartItem = null
                                showDeleteCartItem = it
                            }

                            CartOption.DELETE_ALL -> {
                                showOperationCartItem = null
                                showDeleteAllCart = true
                            }

                            CartOption.SAVE_CART -> {
                                showOperationCartItem = null
                                showResumeCart = true
                            }
                        }
                    }
                }
            }
        }

        showDeleteCartItem?.let {
            ConfirmDialog(
                title = stringResource(Res.string.tx_global_confirmation),
                description = stringResource(Res.string.tx_cart_confirm_delete_product),
                primaryButtonText = stringResource(Res.string.tx_global_confirm_delete),
                onPrimaryClick = { viewModel.removeItemToCart(it.id) },
                secondaryButtonText = stringResource(Res.string.tx_global_cancel),
                onSecondaryClick = { showDeleteCartItem = null },
                onDismiss = { showDeleteCartItem = null }
            )
        }

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