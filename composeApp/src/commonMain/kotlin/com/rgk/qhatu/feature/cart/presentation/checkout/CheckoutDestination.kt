package com.rgk.qhatu.feature.cart.presentation.checkout

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.bottombar.CartSummarySection
import com.rgk.qhatu.common.components.bottomsheet.CustomBottomSheet
import com.rgk.qhatu.common.components.search.SearchContent
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.navigation.ProvideBottomBarApp
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_checkout_confirm

@Serializable
data object CheckoutDestination

internal fun NavGraphBuilder.checkoutDestination(
    setLoading: (Boolean) -> Unit,
    onBackPopUp: () -> Unit,
) {
    composable<CheckoutDestination> {
        val viewModel: CheckoutViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val formState by viewModel.formState.collectAsState()
        val customerList by viewModel.customerList.collectAsState()
        val methodPaymentList by viewModel.methodPaymentList.collectAsState()
        val cartSummary by viewModel.cartSummary.collectAsState()

        var selectedCustomerToClick by remember { mutableStateOf(false) }
        var selectedCustomer by remember { mutableStateOf<Customer?>(null) }
        var queryCustomer by remember { mutableStateOf("") }

        ProvideBottomBarApp {
            if (cartSummary?.hasItems ?: false) {
                CartSummarySection(
                    shoppingCartTotal = cartSummary?.totalSummary.orEmpty(),
                    onShoppingCartClick = {
                        println("CHECKOUT: ${formState.fields}")
                    },
                    textConfirmButton = stringResource(Res.string.tx_checkout_confirm)
                )
            }
        }

        CheckoutScreen(
            uiState = uiState,
            formState = formState,
            onFieldChange = { viewModel.onFieldChange(it) },
            onClearCustomer = { selectedCustomer = null },
            selectedCustomer = selectedCustomer,
            onCustomerClick = {
                viewModel.searchCustomer()
                selectedCustomerToClick = true
            },
            methodPayments = methodPaymentList,
            cartSummary = cartSummary,
            onBackPopUp = onBackPopUp,
            setLoading = setLoading,
        )

        if (selectedCustomerToClick) {
            CustomBottomSheet(isVisible = true, onDismiss = { selectedCustomerToClick = false }) {
                SearchContent(
                    items = customerList,
                    keySelector = { it.id },
                    valueSelector = { it.nameCustomer },
                    query = queryCustomer,
                    onQueryChange = {
                        queryCustomer = it
                        viewModel.onSearchCustomer(it)
                    },
                    onSelectItem = {
                        queryCustomer = ""
                        selectedCustomer = it
                        selectedCustomerToClick = false
                    }
                )
            }
        }
    }
}