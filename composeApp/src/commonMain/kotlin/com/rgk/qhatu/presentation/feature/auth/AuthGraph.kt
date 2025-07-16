package com.rgk.qhatu.presentation.feature.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.presentation.feature.auth.ui.auth.AuthDestination
import com.rgk.qhatu.presentation.feature.auth.ui.auth.authDestination
import kotlinx.serialization.Serializable

@Serializable
data object AuthGraph

fun NavController.navigateToAuthGraph(navOptions: NavOptions? = null) {
    navigate(AuthGraph, navOptions)
}

fun NavGraphBuilder.authGraph(
    navigateToHomeGraph: () -> Unit
) {
    navigation<AuthGraph>(
        startDestination = AuthDestination
    ) {
        authDestination(
            navigateToHome = navigateToHomeGraph
        )
    }
}