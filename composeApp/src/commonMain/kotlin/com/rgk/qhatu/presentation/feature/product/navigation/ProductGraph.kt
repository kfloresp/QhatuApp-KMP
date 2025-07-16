package com.rgk.qhatu.presentation.feature.product.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

@Serializable
data object ProductGraph

fun NavController.navigateToProduct(navOptions: NavOptions? = null) {
    navigate(ProductGraph, navOptions)
}

fun NavGraphBuilder.productGraph(
) {
    navigation<ProductGraph>(
        startDestination = ProductDestination
    ) {
        productDestination()
    }
}