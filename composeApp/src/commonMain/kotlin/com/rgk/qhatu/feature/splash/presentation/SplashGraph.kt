package com.rgk.qhatu.feature.splash.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.feature.splash.presentation.splash.SplashDestination
import com.rgk.qhatu.feature.splash.presentation.splash.splashDestination
import kotlinx.serialization.Serializable

@Serializable
data object SplashGraph

fun NavController.navigateToSplash(navOptions: NavOptions? = null) {
    navigate(SplashGraph, navOptions)
}

fun NavGraphBuilder.splashGraph(
    navigateToAuthGraph: () -> Unit,
    navigateToHomeGraph: () -> Unit,
) {
    navigation<SplashGraph>(
        startDestination = SplashDestination
    ) {
        splashDestination(
            navigateToAuthGraph = navigateToAuthGraph,
            navigateToHomeGraph = navigateToHomeGraph
        )
    }
}