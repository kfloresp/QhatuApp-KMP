package com.rgk.qhatu.feature.auth.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.feature.auth.presentation.auth.AuthDestination
import com.rgk.qhatu.feature.auth.presentation.auth.authDestination
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