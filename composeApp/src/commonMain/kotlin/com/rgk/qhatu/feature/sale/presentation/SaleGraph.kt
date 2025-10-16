package com.rgk.qhatu.feature.sale.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.feature.sale.presentation.sale.SaleDestination
import com.rgk.qhatu.feature.sale.presentation.sale.saleDestination
import com.rgk.qhatu.feature.sale.presentation.saledetail.SaleDetailDestination
import com.rgk.qhatu.feature.sale.presentation.saledetail.saleDetailDestination
import kotlinx.serialization.Serializable

@Serializable
data object SaleGraph

fun NavController.navigateToSaleGraph(navOptions: NavOptions? = null) {
    navigate(SaleGraph, navOptions)
}

fun NavGraphBuilder.saleGraph(
    navController: NavController,
    navigateToCart: () -> Unit,
) {
    navigation<SaleGraph>(
        startDestination = SaleDestination
    ) {
        saleDestination(
            navigateToCart = navigateToCart,
            navigateToSaleDetail = { navController.navigate(SaleDetailDestination) }
        )
        saleDetailDestination(onBackPopUp = { navController.popBackStack() })
    }
}