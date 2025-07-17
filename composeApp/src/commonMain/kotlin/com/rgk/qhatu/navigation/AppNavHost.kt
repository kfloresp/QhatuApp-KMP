package com.rgk.qhatu.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rgk.qhatu.common.extension.navigateToAuthGraphWithPopUp
import com.rgk.qhatu.common.extension.navigateToHomeWithPopUp
import com.rgk.qhatu.feature.auth.presentation.authGraph
import com.rgk.qhatu.feature.customer.presentation.customerGraph
import com.rgk.qhatu.feature.customer.presentation.navigateToCustomer
import com.rgk.qhatu.feature.home.presentation.homeGraph
import com.rgk.qhatu.feature.home.presentation.navigateToHomeGraph
import com.rgk.qhatu.feature.payment.presentation.navigateToPayment
import com.rgk.qhatu.feature.payment.presentation.paymentGraph
import com.rgk.qhatu.feature.product.presentation.navigateToProduct
import com.rgk.qhatu.feature.product.presentation.productGraph
import com.rgk.qhatu.feature.sale.presentation.navigateToSale
import com.rgk.qhatu.feature.sale.presentation.saleGraph
import com.rgk.qhatu.feature.search.presentation.navigateToSearchGraph
import com.rgk.qhatu.feature.search.presentation.searchGraph
import com.rgk.qhatu.feature.setting.presentation.setting.component.SettingType
import com.rgk.qhatu.feature.setting.presentation.navigateToSetting
import com.rgk.qhatu.feature.setting.presentation.settingGraph
import com.rgk.qhatu.feature.splash.presentation.SplashGraph
import com.rgk.qhatu.feature.splash.presentation.splashGraph
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
