package com.rgk.qhatu.presentation.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

@Serializable
data object SearchGraph

fun NavController.navigateToSearchGraph(navOptions: NavOptions? = null) {
    navigate(SearchGraph, navOptions)
}

fun NavGraphBuilder.searchGraph(
    navigateToHome: () -> Unit,
    navigateToCart: () -> Unit,
    openScanQR: () -> Unit
) {
    navigation<SearchGraph>(
        startDestination = SearchDestination
    ) {
        searchDestination(
            navigateToHome = navigateToHome,
            navigateToCart = navigateToCart,
            openScanQR = openScanQR
        )
    }
}