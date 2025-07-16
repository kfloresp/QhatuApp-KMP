package com.rgk.qhatu.feature.sale.presentation.sale
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object SaleDestination

internal fun NavGraphBuilder.saleDestination(
) {
    composable<SaleDestination> {
        SaleScreen()
    }
}