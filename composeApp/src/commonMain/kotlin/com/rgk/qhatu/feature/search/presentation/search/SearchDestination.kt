package com.rgk.qhatu.feature.search.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.cart.CartIconWithBadge
import com.rgk.qhatu.feature.product.presentation.product.ProductScreen
import com.rgk.qhatu.feature.product.presentation.product.ProductViewModel
import com.rgk.qhatu.navigation.ProvideAppBar
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object SearchDestination

internal fun NavGraphBuilder.searchDestination(
    navigateToCart: () -> Unit,
    openScanQR: () -> Unit,
    onProductClick: (String) -> Unit,
) {
    composable<SearchDestination> {
        val viewModel: ProductViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val isRefreshing by viewModel.isRefreshing.collectAsState()
        ProvideAppBar(
            actions = {
                Text(
                    text = "S/1000.0",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.width(8.dp))
                VerticalDivider(
                    Modifier
                        .height(24.dp)
                        .width(1.dp), thickness = 1.dp
                )
                Spacer(Modifier.width(8.dp))
                CartIconWithBadge(
                    itemCount = 100,
                    modifier = Modifier.clickable(true, onClick = navigateToCart)
                )
            }
        )

        SearchScreen(
            uiState = uiState,
            onQueryChange = viewModel::onQueryChanged,
            onItemClick = {
                onProductClick(it.id)
            },
            onPullRefresh = viewModel::onPullRefresh,
            isRefreshing = isRefreshing,
        )
    }
}