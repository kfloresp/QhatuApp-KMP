package com.rgk.qhatu.feature.splash.presentation.splash

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object SplashDestination

internal fun NavGraphBuilder.splashDestination(
    navigateToAuthGraph: () -> Unit,
    navigateToHomeGraph: () -> Unit
) {
    composable<SplashDestination> {
        val viewModel: SplashViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        SplashScreen(
            uiState = uiState,
            navigateToAuthGraph = navigateToAuthGraph,
            navigateToHomeGraph = navigateToHomeGraph
        )
    }
}