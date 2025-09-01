package com.rgk.qhatu.feature.home.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.feature.home.presentation.home.HomeDestination
import com.rgk.qhatu.feature.home.presentation.home.homeDestination
import kotlinx.serialization.Serializable

@Serializable
data object HomeGraph

fun NavController.navigateToHomeGraph(navOptions: NavOptions? = null) {
    navigate(HomeGraph, navOptions)
}

fun NavGraphBuilder.homeGraph(
    setLoading: (Boolean) -> Unit,
    navigateToSearch: () -> Unit,
    navigateToSale: () -> Unit,
    navigateToPayment: () -> Unit,
    navigateToProduct: () -> Unit,
    navigateToCart: () -> Unit,
) {
    navigation<HomeGraph>(
        startDestination = HomeDestination
    ) {
        homeDestination(
            setLoading = setLoading,
            navigateToSearch = navigateToSearch,
            navigateToSale = navigateToSale,
            navigateToPayment = navigateToPayment,
            navigateToProduct = navigateToProduct,
            navigateToCart = navigateToCart
        )
    }
}