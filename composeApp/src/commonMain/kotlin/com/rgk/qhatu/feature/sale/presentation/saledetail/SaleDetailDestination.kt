package com.rgk.qhatu.feature.sale.presentation.saledetail

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.navigation.ProvideAppBar
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data class SaleDetailDestination(val operationId: String)

internal fun NavGraphBuilder.saleDetailDestination(
) {
    composable<SaleDetailDestination> {
        val viewModel: SaleDetailViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()

        ProvideAppBar(
            showBackNavigation = false,
        )

        SaleDetailScreen(uiState)
    }
}