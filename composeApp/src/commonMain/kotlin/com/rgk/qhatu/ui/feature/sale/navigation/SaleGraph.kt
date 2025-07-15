package com.rgk.qhatu.ui.feature.sale.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

@Serializable
data object SaleGraph

fun NavController.navigateToSale(navOptions: NavOptions? = null) {
    navigate(SaleGraph, navOptions)
}

fun NavGraphBuilder.saleGraph(
) {
    navigation<SaleGraph>(
        startDestination = SaleDestination
    ) {
        saleDestination()
    }
}