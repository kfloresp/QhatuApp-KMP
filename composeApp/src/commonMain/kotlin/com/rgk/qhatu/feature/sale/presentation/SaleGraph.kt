package com.rgk.qhatu.feature.sale.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.feature.sale.presentation.sale.SaleDestination
import com.rgk.qhatu.feature.sale.presentation.sale.saleDestination
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