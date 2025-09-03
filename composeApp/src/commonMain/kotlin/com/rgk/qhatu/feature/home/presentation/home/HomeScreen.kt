package com.rgk.qhatu.feature.home.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.banner.BannerSection
import com.rgk.qhatu.common.components.search.SearchBar
import com.rgk.qhatu.feature.home.presentation.component.ActionItemCard
import com.rgk.qhatu.feature.home.presentation.component.provideMenu
import com.rgk.qhatu.feature.product.presentation.productform.SectionHeader
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_cart_pending

@Composable
fun HomeScreen(
    uiState: HomeScreenUiState,
    navigateToSearch: () -> Unit,
    navigateToSale: () -> Unit,
    navigateToPayment: () -> Unit,
    navigateToProduct: () -> Unit,
    navigateToCartsInactive: () -> Unit,
    setLoading: (Boolean) -> Unit,
) {
    val menuSections = provideMenu(
        onSalesClick = navigateToSale,
        onPaymentsClick = navigateToPayment,
        onProductsClick = navigateToProduct,
    )
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        SearchBar {
            navigateToSearch()
        }
        if (uiState.cartUiState is CartUiState.Success) {
            val cartCount = uiState.cartUiState.carts.size
            ActionItemCard(
                stringResource(Res.string.tx_cart_pending, cartCount),
                icon = Icons.Default.ShoppingCart,
                onClick = {
                    navigateToCartsInactive()
                }
            )
        }
        BannerSection(
            path = "/data/user/0/com.rgk.ingenieros/files/IMG_1756692810261.png",
            modifier = Modifier
                .height(180.dp)
        )
        menuSections.forEach { section ->
            SectionHeader(section.title)

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                section.actions.forEach { action ->
                    Box(
                        modifier = Modifier.width(160.dp)
                    ) {
                        ActionItemCard(
                            text = stringResource(action.text),
                            icon = action.icon,
                            onClick = action.navigateTo
                        )
                    }
                }
            }
        }
    }
    setLoading(uiState.homeUiState is HomeUiState.Loading)
}
