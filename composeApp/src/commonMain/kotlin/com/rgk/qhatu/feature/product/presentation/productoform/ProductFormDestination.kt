package com.rgk.qhatu.feature.product.presentation.productoform

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data class ProductFormDestination(val idProduct: String)

internal fun NavGraphBuilder.productFormDestination(
    onBackPopUp: () -> Unit,
    onDeletePopUp: () -> Unit,
) {
    composable<ProductFormDestination> { destination ->
        val viewModel: ProductFormViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()

        ProductFormScreen(uiState)
    }
}