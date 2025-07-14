package com.rgk.qhatu.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rgk.qhatu.ui.feature.auth.navigation.authGraph
import com.rgk.qhatu.ui.feature.home.navigation.homeGraph
import com.rgk.qhatu.ui.feature.home.navigation.navigateToHomeGraph
import com.rgk.qhatu.ui.feature.search.navigation.navigateToSearchGraph
import com.rgk.qhatu.ui.feature.search.navigation.searchGraph
import com.rgk.qhatu.ui.feature.splash.navigation.SplashGraph
import com.rgk.qhatu.ui.feature.splash.navigation.splashGraph
import com.rgk.qhatu.utils.navigateToAuthGraphWithPopUp
import com.rgk.qhatu.utils.navigateToHomeWithPopUp

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SplashGraph
    ) {
        splashGraph(
            navigateToAuthGraph = {
                navController.navigateToAuthGraphWithPopUp()
            },
            navigateToHomeGraph = {
                navController.navigateToHomeWithPopUp()
            }
        )
        authGraph(
            navigateToHomeGraph = { navController.navigateToHomeGraph() }
        )
        homeGraph(
            navigateToSearch = { navController.navigateToSearchGraph() },
            navigateToSale = {},
            navigateToConfiguration = {},
            navigateToPayment = {},
            navigateToProduct = {},
            navigateToClient = {},
        )
        searchGraph(
            navigateToHome = {},
            navigateToCart = {},
            openScanQR = {})
    }
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            TopBarApp(
                navController = navController
            )
        },
        bottomBar = {
            BottomBarApp(
                navController = navController
            )
        },
    ) { innerPadding ->
        AppNavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }
}

//        composable(
//            route = RouteNavigation.SearchProvider.src,
//            arguments = listOf(navArgument(RouteNavigation.SearchProvider.Args.QUERY) { type = NavType.StringType })
//        ) { backstackEntry ->
//            val query = checkNotNull(backstackEntry.arguments?.getString(RouteNavigation.SearchProvider.Args.QUERY))
//            SearchProviderScreen(
//                viewModel = koinViewModel(parameters = { parametersOf(query) }),
//                navController = navController
//            )
//        }
//    }
