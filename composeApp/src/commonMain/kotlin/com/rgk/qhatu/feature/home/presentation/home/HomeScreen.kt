package com.rgk.qhatu.feature.home.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.feature.home.presentation.component.ActionItemCard
import com.rgk.qhatu.feature.home.presentation.component.HomeHeader
import com.rgk.qhatu.feature.home.presentation.component.provideMenu
import com.rgk.qhatu.feature.product.presentation.productform.SectionHeader
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    navigateToSearch: () -> Unit,
    navigateToSale: () -> Unit,
    navigateToPayment: () -> Unit,
    navigateToCustomer: () -> Unit,
    navigateToProduct: () -> Unit,
    navigateToSetting: () -> Unit,
    setLoading: (Boolean) -> Unit,
) {
    val menuSections = provideMenu(
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
            .padding(16.dp)
    ) {
        HomeHeader(name = "Nick")
        Spacer(modifier = Modifier.height(24.dp))
        menuSections.forEach { section ->
            SectionHeader(section.title)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                items(section.actions) { action ->
                    ActionItemCard(
                        text = stringResource(action.text),
                        icon = action.icon,
                        onClick = action.navigateTo
                    )
                }
            }
        }
    }
    setLoading(uiState is HomeUiState.Loading)
}
