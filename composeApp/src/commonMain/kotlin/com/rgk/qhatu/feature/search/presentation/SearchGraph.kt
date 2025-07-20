package com.rgk.qhatu.feature.search.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.feature.search.presentation.search.SearchDestination
import com.rgk.qhatu.feature.search.presentation.search.searchDestination
import kotlinx.serialization.Serializable

@Serializable
data object SearchGraph

fun NavController.navigateToSearchGraph(navOptions: NavOptions? = null) {
    navigate(SearchGraph, navOptions)
}

fun NavGraphBuilder.searchGraph(
    navigateToCart: () -> Unit,
    openScanQR: () -> Unit,
) {
    navigation<SearchGraph>(
        startDestination = SearchDestination
    ) {
        searchDestination(
            navigateToCart = navigateToCart,
            openScanQR = openScanQR,
        )
    }
}