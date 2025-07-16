package com.rgk.qhatu.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rgk.qhatu.presentation.feature.auth.authGraph
import com.rgk.qhatu.presentation.feature.customer.customerGraph
import com.rgk.qhatu.presentation.feature.customer.navigateToCustomer
import com.rgk.qhatu.presentation.feature.home.navigation.homeGraph
import com.rgk.qhatu.presentation.feature.home.navigation.navigateToHomeGraph
import com.rgk.qhatu.presentation.feature.payment.navigation.navigateToPayment
import com.rgk.qhatu.presentation.feature.payment.navigation.paymentGraph
import com.rgk.qhatu.presentation.feature.product.navigation.navigateToProduct
import com.rgk.qhatu.presentation.feature.product.navigation.productGraph
import com.rgk.qhatu.presentation.feature.sale.navigation.navigateToSale
import com.rgk.qhatu.presentation.feature.sale.navigation.saleGraph
import com.rgk.qhatu.presentation.feature.search.navigation.navigateToSearchGraph
import com.rgk.qhatu.presentation.feature.search.navigation.searchGraph
import com.rgk.qhatu.presentation.feature.setting.feature.setting.component.SettingType
import com.rgk.qhatu.presentation.feature.setting.navigateToSetting
import com.rgk.qhatu.presentation.feature.setting.settingGraph
import com.rgk.qhatu.presentation.feature.splash.navigation.SplashGraph
import com.rgk.qhatu.presentation.feature.splash.navigation.splashGraph
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
            navigateToSale = { navController.navigateToSale() },
            navigateToSetting = { navController.navigateToSetting() },
            navigateToPayment = { navController.navigateToPayment() },
            navigateToProduct = { navController.navigateToProduct() },
            navigateToCustomer = { navController.navigateToCustomer() },
        )
        searchGraph(
            navigateToHome = {},
            navigateToCart = {},
            openScanQR = {})
        saleGraph()
        settingGraph(onOptionClick = { settingType ->
            when (settingType) {
                SettingType.PROFILE -> {}
                SettingType.CATEGORIES -> {}
                SettingType.BRANDS -> {}
                SettingType.UNITS -> {}
                SettingType.SYNC_DATA -> {}
                SettingType.EXPORT_DATA -> {}
                SettingType.LOGOUT -> {
                    navController.navigateToAuthGraphWithPopUp()
                }
            }
        })
        paymentGraph()
        productGraph()
        customerGraph()
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
