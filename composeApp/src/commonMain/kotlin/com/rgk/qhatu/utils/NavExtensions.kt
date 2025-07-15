package com.rgk.qhatu.utils

import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.rgk.qhatu.ui.feature.auth.navigation.AuthGraph
import com.rgk.qhatu.ui.feature.auth.navigation.navigateToAuthGraph
import com.rgk.qhatu.ui.feature.home.navigation.HomeGraph
import com.rgk.qhatu.ui.feature.home.navigation.navigateToHomeGraph

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