package com.rgk.qhatu.feature.product.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.common.extension.navigateToHomeWithPopUp
import com.rgk.qhatu.feature.product.presentation.product.ProductDestination
import com.rgk.qhatu.feature.product.presentation.product.productDestination
import com.rgk.qhatu.feature.product.presentation.productoform.ProductFormDestination
import com.rgk.qhatu.feature.product.presentation.productoform.productFormDestination
import kotlinx.serialization.Serializable

@Serializable
data object ProductGraph

fun NavController.navigateToProductGraph(navOptions: NavOptions? = null) {
    navigate(ProductGraph, navOptions)
}

fun NavGraphBuilder.productGraph(
    navController: NavController,
) {
    navigation<ProductGraph>(
        startDestination = ProductDestination
    ) {
        productDestination(onBackPopUp = {
            navController.navigateToHomeWithPopUp()
        }, onProductClick = {
            navController.navigate(ProductFormDestination(it))
        }, onNewProductClick = {
            navController.navigate(ProductFormDestination(""))
        })
        productFormDestination(onBackPopUp = {
            navController.popBackStack()
        }, onDeletePopUp = {
            navController.popBackStack()
        })
    }
}