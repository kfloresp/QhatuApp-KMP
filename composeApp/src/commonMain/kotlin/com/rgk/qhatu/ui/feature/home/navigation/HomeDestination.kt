package com.rgk.qhatu.ui.feature.home.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.ui.feature.home.HomeScreen
import com.rgk.qhatu.ui.feature.home.HomeViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object HomeDestination

internal fun NavGraphBuilder.homeDestination(
    navigateToSearch: () -> Unit,
    navigateToSale: () -> Unit,
    navigateToPayment: () -> Unit,
    navigateToCustomer: () -> Unit,
    navigateToProduct: () -> Unit,
    navigateToSetting: () -> Unit
) {
    composable<HomeDestination> {
        val viewModel: HomeViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        HomeScreen(
            uiState = uiState,
            navigateToSearch = navigateToSearch,
            navigateToSale = navigateToSale,
            navigateToPayment = navigateToPayment,
            navigateToCustomer = navigateToCustomer,
            navigateToProduct = navigateToProduct,
            navigateToSetting = navigateToSetting
        )
    }
}