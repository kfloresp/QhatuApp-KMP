package com.rgk.qhatu.presentation.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

@Serializable
data object HomeGraph

fun NavController.navigateToHomeGraph(navOptions: NavOptions? = null) {
    navigate(HomeGraph, navOptions)
}

fun NavGraphBuilder.homeGraph(
    navigateToSearch: () -> Unit,
    navigateToSale: () -> Unit,
    navigateToPayment: () -> Unit,
    navigateToCustomer: () -> Unit,
    navigateToProduct: () -> Unit,
    navigateToSetting: () -> Unit
) {
    navigation<HomeGraph>(
        startDestination = HomeDestination
    ) {
        homeDestination(
            navigateToSearch = navigateToSearch,
            navigateToSale = navigateToSale,
            navigateToPayment = navigateToPayment,
            navigateToCustomer = navigateToCustomer,
            navigateToProduct = navigateToProduct,
            navigateToSetting = navigateToSetting
        )
    }
}