package com.rgk.qhatu.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.rgk.qhatu.MainViewModel
import com.rgk.qhatu.common.components.loading.LoadingOverlay
import com.rgk.qhatu.common.extension.navigateToAuthGraphWithPopUp
import com.rgk.qhatu.common.extension.navigateToHomeWithPopUp
import com.rgk.qhatu.feature.auth.presentation.authGraph
import com.rgk.qhatu.feature.cart.presentation.cartGraph
import com.rgk.qhatu.feature.cart.presentation.navigateToCartGraph
import com.rgk.qhatu.feature.customer.presentation.customerGraph
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
import com.rgk.qhatu.feature.setting.presentation.settingGraph
import com.rgk.qhatu.feature.splash.presentation.SplashGraph
import com.rgk.qhatu.feature.splash.presentation.splashGraph

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
    closeSession: () -> Unit,
    setLoading: (Boolean) -> Unit,
) {
    NavHost(
        modifier = modifier, navController = navController, startDestination = SplashGraph
    ) {
        splashGraph(navigateToAuthGraph = {
            navController.navigateToAuthGraphWithPopUp()
        }, navigateToHomeGraph = {
            navController.navigateToHomeWithPopUp()
        })
        authGraph(
            navigateToHomeGraph = { navController.navigateToHomeGraph() })
        homeGraph(
            setLoading = setLoading,
            navigateToSearch = { navController.navigateToSearchGraph() },
            navigateToSale = { navController.navigateToSaleGraph() },
            navigateToPayment = { navController.navigateToPaymentGraph() },
            navigateToProduct = { navController.navigateToProductGraph() },
            navigateToCart = { navController.navigateToCartGraph() })
        searchGraph(navigateToCart = { navController.navigateToCartGraph() }, openScanQR = {})
        saleGraph(
            navController = navController,
            navigateToCart = { navController.navigateToCartGraph() },
        )
        cartGraph(
            navController = navController,
            setLoading = setLoading,
            navigateToHome = { navController.navigateToHomeGraph() }
        )
        settingGraph(
            navController = navController,
            closeSession = closeSession,
        )
        paymentGraph(navController = navController)
        productGraph(navController = navController)
        customerGraph(
            navController = navController,
            navigateToCart = { navController.navigateToCartGraph() })
    }
}

@Composable
fun AppNavGraph(
    viewModel: MainViewModel,
    navController: NavHostController,
    closeSession: () -> Unit,
) {
    val isLoading by viewModel.isLoading.collectAsState()
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            TopBarApp(
                navController = navController
            )
        },
        floatingActionButton = {
            FloatingActionButtonApp(
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
            setLoading = { active ->
                if (active) viewModel.showLoading()
                else viewModel.hideLoading()
            })
    }
    if (isLoading) {
        LoadingOverlay()
    }
}