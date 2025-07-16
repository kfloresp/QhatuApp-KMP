package com.rgk.qhatu.feature.product.presentation.product

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ProductDestination

internal fun NavGraphBuilder.productDestination(
) {
    composable<ProductDestination> {
        ProductScreen()
    }
}