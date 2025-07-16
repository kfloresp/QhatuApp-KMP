package com.rgk.qhatu.ui.feature.sale.navigation
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.ui.feature.sale.SaleScreen
import kotlinx.serialization.Serializable

@Serializable
data object SaleDestination

internal fun NavGraphBuilder.saleDestination(
) {
    composable<SaleDestination> {
        SaleScreen()
    }
}