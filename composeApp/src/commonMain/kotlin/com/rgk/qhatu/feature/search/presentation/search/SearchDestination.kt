package com.rgk.qhatu.feature.search.presentation.search

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
import com.rgk.qhatu.navigation.ProvideAppBarActions
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object SearchDestination

internal fun NavGraphBuilder.searchDestination(
    navigateToCart: () -> Unit,
    openScanQR: () -> Unit,
) {
    composable<SearchDestination> {
        val viewModel: SearchViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()

        ProvideAppBarActions {
            Text(
                text = "S/1000.0",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.width(8.dp))
            VerticalDivider( Modifier
                .height(24.dp)
                .width(1.dp),thickness = 1.dp)
            Spacer(Modifier.width(8.dp))
            CartIconWithBadge(itemCount = 100)
        }

        SearchScreen(
            uiState = uiState,
            navigateToCart = navigateToCart,
            openScanQR = openScanQR
        )
    }
}