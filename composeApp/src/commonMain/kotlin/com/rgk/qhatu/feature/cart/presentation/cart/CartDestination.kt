package com.rgk.qhatu.feature.cart.presentation.cart

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CartDestination

internal fun NavGraphBuilder.cartDestination(
) {
    composable<CartDestination> {
        CartScreen()
    }
}