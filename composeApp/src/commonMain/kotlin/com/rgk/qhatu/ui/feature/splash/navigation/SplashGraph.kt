package com.rgk.qhatu.ui.feature.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
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