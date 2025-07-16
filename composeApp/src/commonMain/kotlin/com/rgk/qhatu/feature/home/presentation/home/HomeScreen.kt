package com.rgk.qhatu.feature.home.presentation.home

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
import com.rgk.qhatu.feature.home.presentation.component.ActionGrid
import com.rgk.qhatu.feature.home.presentation.component.HomeHeader
import com.rgk.qhatu.feature.home.presentation.component.provideMenu
import com.rgk.qhatu.feature.splash.presentation.splash.SplashUiState

@Composable
fun HomeScreen(
    uiState: SplashUiState,
    navigateToSearch: () -> Unit,
    navigateToSale: () -> Unit,
    navigateToPayment: () -> Unit,
    navigateToCustomer: () -> Unit,
    navigateToProduct: () -> Unit,
    navigateToSetting: () -> Unit
) {
    val actions = provideMenu(
        onSearchClick = navigateToSearch,
        onSalesClick = navigateToSale,
        onPaymentsClick = navigateToPayment,
        onCustomersClick = navigateToCustomer,
        onProductsClick = navigateToProduct,
        onSettingsClick = navigateToSetting
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
