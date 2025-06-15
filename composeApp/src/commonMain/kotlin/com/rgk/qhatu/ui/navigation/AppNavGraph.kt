package com.rgk.qhatu.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rgk.qhatu.ui.feature.login.LoginScreen
import com.rgk.qhatu.ui.feature.home.HomeScreen
import com.rgk.qhatu.ui.feature.movement.MovementScreen
import com.rgk.qhatu.ui.feature.profile.ProfileScreen
import com.rgk.qhatu.ui.feature.receipt.ReceiptScreen
import com.rgk.qhatu.ui.feature.receipt.receiptdetail.ReceiptDetailScreen
import com.rgk.qhatu.ui.feature.sale.SaleScreen
import com.rgk.qhatu.ui.feature.search.SearchScreen
import com.rgk.qhatu.ui.feature.search.advancedsearch.AdvancedSearchScreen
import com.rgk.qhatu.ui.feature.settings.SettingsScreen
import com.rgk.qhatu.ui.feature.splash.SplashScreen
import com.rgk.qhatu.ui.feature.sync.SyncScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RouteNavigation.Splash.src) {
        composable(RouteNavigation.Splash.src) {
            SplashScreen(navController)
        }
        composable(RouteNavigation.Login.src) {
            LoginScreen(navController)
        }
        composable(RouteNavigation.Home.src) {
            HomeScreen(navController)
        }
        composable(RouteNavigation.Sync.src) {
            SyncScreen(navController)
        }
        composable(RouteNavigation.Search.src) {
            SearchScreen(navController)
        }
        composable(RouteNavigation.Receipt.src) {
            ReceiptScreen(navController)
        }
        composable(RouteNavigation.Setting.src) {
            SettingsScreen(navController)
        }
        composable(RouteNavigation.Sale.src) {
            SaleScreen(navController)
        }
        composable(RouteNavigation.Movement.src) {
            MovementScreen(navController)
        }
        composable(RouteNavigation.Profile.src) {
            ProfileScreen(navController)
        }
        composable(
            route = RouteNavigation.AdvancedSearch.src,
            arguments = listOf(navArgument(RouteNavigation.AdvancedSearch.Args.Query) { type = NavType.StringType },
                navArgument(RouteNavigation.AdvancedSearch.Args.SearchType) { type = NavType.IntType })
        ) { backstackEntry ->
            val query = checkNotNull(backstackEntry.arguments?.getString(RouteNavigation.AdvancedSearch.Args.Query))
            val searchType = checkNotNull(backstackEntry.arguments?.getInt(RouteNavigation.AdvancedSearch.Args.SearchType))
            AdvancedSearchScreen(
                viewModel = koinViewModel(parameters = { parametersOf(query, searchType) }),
                navController = navController
            )
        }
        composable(RouteNavigation.ReceiptDetail.src) {
            ReceiptDetailScreen(navController)
        }
    }
}
