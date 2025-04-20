package com.rgk.qhatu.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rgk.qhatu.ui.feature.login.LoginScreen
import com.rgk.qhatu.ui.feature.home.HomeScreen
import com.rgk.qhatu.ui.feature.search.SearchScreen
import com.rgk.qhatu.ui.feature.search.advancedsearch.AdvancedSearchScreen
import com.rgk.qhatu.ui.feature.splash.SplashScreen
import com.rgk.qhatu.ui.feature.sync.SyncScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

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
        composable("search") {
            SearchScreen(navController)
        }
        composable(
            route = "advanced-search/{query}/{searchType}",
            arguments = listOf(navArgument("query") { type = NavType.StringType },
                navArgument("searchType") { type = NavType.IntType })
        ) { backstackEntry ->
            val query = checkNotNull(backstackEntry.arguments?.getString("query"))
            val searchType = checkNotNull(backstackEntry.arguments?.getInt("searchType"))
            AdvancedSearchScreen(
                viewModel = koinViewModel(parameters = { parametersOf(query, searchType) }),
                navController = navController
            )
        }
    }
}
