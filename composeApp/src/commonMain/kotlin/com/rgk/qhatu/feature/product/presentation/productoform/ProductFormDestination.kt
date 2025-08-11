package com.rgk.qhatu.feature.product.presentation.productoform

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class ProductFormDestination(val idProduct: String)

internal fun NavGraphBuilder.productFormDestination(
    onBackPopUp: () -> Unit,
    onDeletePopUp: () -> Unit,
) {
    composable<ProductFormDestination> { destination ->

    }
}