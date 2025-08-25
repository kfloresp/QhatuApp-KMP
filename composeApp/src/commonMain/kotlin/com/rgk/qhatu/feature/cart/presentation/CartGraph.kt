package com.rgk.qhatu.feature.cart.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.feature.cart.presentation.cart.CartDestination
import com.rgk.qhatu.feature.cart.presentation.cart.cartDestination
import com.rgk.qhatu.feature.cart.presentation.checkout.CheckoutDestination
import com.rgk.qhatu.feature.cart.presentation.checkout.checkoutDestination
import com.rgk.qhatu.feature.search.presentation.search.SearchDestination
import com.rgk.qhatu.feature.search.presentation.search.searchDestination
import kotlinx.serialization.Serializable

@Serializable
data object CartGraph

fun NavController.navigateToCartGraph(navOptions: NavOptions? = null) {
    navigate(CartGraph, navOptions)
}

fun NavGraphBuilder.cartGraph(
    navController: NavController,
    setLoading: (Boolean) -> Unit,
) {
    navigation<CartGraph>(
        startDestination = CartDestination
    ) {
        cartDestination(
            setLoading = setLoading,
            onBackPopUp = {
                navController.popBackStack()
            },
            navigateToCheckout = {
                navController.navigate(CheckoutDestination)
            }
        )
        checkoutDestination(
            setLoading = setLoading,
            onBackPopUp = { navController.popBackStack() },
        )
    }
}