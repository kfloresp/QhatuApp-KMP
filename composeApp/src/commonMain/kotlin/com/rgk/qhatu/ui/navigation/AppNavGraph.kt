package com.rgk.qhatu.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rgk.qhatu.ui.feature.login.LoginScreen
import com.rgk.qhatu.ui.feature.home.HomeScreen
import com.rgk.qhatu.ui.feature.splash.SplashScreen
import com.rgk.qhatu.ui.feature.sync.SyncScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("sync") {
            SyncScreen(navController)
        }
    }
}
