package com.rgk.qhatu.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rgk.qhatu.MainViewModel
import com.rgk.qhatu.common.extension.navigateToAuthGraphWithPopUp
import com.rgk.qhatu.common.extension.navigateToHomeWithPopUp
import com.rgk.qhatu.feature.auth.presentation.authGraph
import com.rgk.qhatu.feature.customer.presentation.customerGraph
import com.rgk.qhatu.feature.customer.presentation.navigateToCustomerGraph
import com.rgk.qhatu.feature.home.presentation.homeGraph
import com.rgk.qhatu.feature.home.presentation.navigateToHomeGraph
import com.rgk.qhatu.feature.payment.presentation.navigateToPaymentGraph
import com.rgk.qhatu.feature.payment.presentation.paymentGraph
import com.rgk.qhatu.feature.product.presentation.navigateToProductGraph
import com.rgk.qhatu.feature.product.presentation.productGraph
import com.rgk.qhatu.feature.sale.presentation.navigateToSaleGraph
import com.rgk.qhatu.feature.sale.presentation.saleGraph
import com.rgk.qhatu.feature.search.presentation.navigateToSearchGraph
import com.rgk.qhatu.feature.search.presentation.searchGraph
import com.rgk.qhatu.feature.setting.presentation.navigateToSettingGraph
import com.rgk.qhatu.feature.setting.presentation.settingGraph
import com.rgk.qhatu.feature.splash.presentation.SplashGraph
import com.rgk.qhatu.feature.splash.presentation.splashGraph
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
    closeSession: () -> Unit,
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
            navigateToSale = { navController.navigateToSaleGraph() },
            navigateToSetting = { navController.navigateToSettingGraph() },
            navigateToPayment = { navController.navigateToPaymentGraph() },
            navigateToProduct = { navController.navigateToProductGraph() },
            navigateToCustomer = { navController.navigateToCustomerGraph() },
        )
        searchGraph(
            navigateToCart = {},
            openScanQR = {}
        )
        saleGraph()
        settingGraph(
            navController = navController,
            closeSession = closeSession,
        )
        paymentGraph()
        productGraph()
        customerGraph()
    }
}

@Composable
fun AppNavGraph(
    viewModel: MainViewModel,
    navController: NavHostController,
    closeSession: () -> Unit,
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
            navController = navController,
            closeSession = closeSession,
        )
    }
}