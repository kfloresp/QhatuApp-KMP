package com.rgk.qhatu.feature.product.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.feature.product.presentation.product.ProductDestination
import com.rgk.qhatu.feature.product.presentation.product.productDestination
import kotlinx.serialization.Serializable

@Serializable
data object ProductGraph

fun NavController.navigateToProductGraph(navOptions: NavOptions? = null) {
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