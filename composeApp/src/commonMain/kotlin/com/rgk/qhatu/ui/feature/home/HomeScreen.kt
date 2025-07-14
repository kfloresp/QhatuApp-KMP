package com.rgk.qhatu.ui.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.ui.feature.home.component.ActionGrid
import com.rgk.qhatu.ui.feature.home.component.HomeHeader
import com.rgk.qhatu.ui.feature.home.component.provideMenu
import com.rgk.qhatu.ui.feature.splash.SplashUiState

@Composable
fun HomeScreen(
    uiState: SplashUiState,
    navigateToSearch: () -> Unit,
    navigateToSale: () -> Unit,
    navigateToPayment: () -> Unit,
    navigateToClient: () -> Unit,
    navigateToProduct: () -> Unit,
    navigateToConfiguration: () -> Unit
) {
    val actions = provideMenu(
        onSearchClick = navigateToSearch,
        onSalesClick = navigateToSale,
        onPaymentsClick = navigateToPayment,
        onClientsClick = navigateToClient,
        onProductsClick = navigateToProduct,
        onSettingsClick = navigateToConfiguration
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        HomeHeader(name = "Janice")
        Spacer(modifier = Modifier.height(24.dp))
        ActionGrid(actions)
    }
}
