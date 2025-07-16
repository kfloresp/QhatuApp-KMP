package com.rgk.qhatu.utils

import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.rgk.qhatu.feature.auth.presentation.AuthGraph
import com.rgk.qhatu.feature.auth.presentation.navigateToAuthGraph
import com.rgk.qhatu.feature.home.presentation.HomeGraph
import com.rgk.qhatu.feature.home.presentation.navigateToHomeGraph

fun NavController.navigateToHomeWithPopUp() {
    navigateToHomeGraph(
        navOptions = navOptions {
            popUpTo(HomeGraph) { inclusive = true }
            launchSingleTop = true
        }
    )
}

fun NavController.navigateToAuthGraphWithPopUp() {
    navigateToAuthGraph(
        navOptions = navOptions {
            popUpTo(AuthGraph) { inclusive = true }
            launchSingleTop = true
        }
    )
}