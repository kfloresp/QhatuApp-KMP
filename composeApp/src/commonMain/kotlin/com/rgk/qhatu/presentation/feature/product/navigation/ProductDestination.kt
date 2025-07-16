package com.rgk.qhatu.presentation.feature.product.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.presentation.feature.product.ProductScreen
import kotlinx.serialization.Serializable

@Serializable
data object ProductDestination

internal fun NavGraphBuilder.productDestination(
) {
    composable<ProductDestination> {
        ProductScreen()
    }
}