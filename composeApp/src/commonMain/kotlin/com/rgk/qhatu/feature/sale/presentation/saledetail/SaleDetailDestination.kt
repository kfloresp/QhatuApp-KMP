package com.rgk.qhatu.feature.sale.presentation.saledetail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class SaleDetailDestination(val operationId: String)

internal fun NavGraphBuilder.saleDetailDestination(
    onBackPopUp: () -> Unit,
) {
    composable<SaleDetailDestination> {
      
    }
}