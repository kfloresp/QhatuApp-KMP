package com.rgk.qhatu.ui.feature.search.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.ui.feature.search.SearchScreen
import com.rgk.qhatu.ui.feature.search.SearchViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object SearchDestination

internal fun NavGraphBuilder.searchDestination(
    navigateToHome: () -> Unit,
    navigateToCart: () -> Unit,
    openScanQR: () -> Unit
) {
    composable<SearchDestination> {
        val viewModel: SearchViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        SearchScreen(
            uiState = uiState,
            navigateToHome = navigateToHome,
            navigateToCart = navigateToCart,
            openScanQR = openScanQR
        )
    }
}