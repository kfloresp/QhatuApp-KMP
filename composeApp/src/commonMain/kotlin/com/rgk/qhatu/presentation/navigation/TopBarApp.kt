package com.rgk.qhatu.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rgk.qhatu.presentation.components.toolbar.QhatuCartToolbar
import com.rgk.qhatu.presentation.components.toolbar.QhatuToolbar
import com.rgk.qhatu.presentation.feature.customer.ui.customer.CustomerDestination
import com.rgk.qhatu.presentation.feature.payment.navigation.PaymentDestination
import com.rgk.qhatu.presentation.feature.product.navigation.ProductDestination
import com.rgk.qhatu.presentation.feature.sale.navigation.SaleDestination
import com.rgk.qhatu.presentation.feature.search.navigation.SearchDestination
import com.rgk.qhatu.presentation.feature.setting.feature.setting.SettingDestination
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.title_customers
import qhatuapp.composeapp.generated.resources.title_payments
import qhatuapp.composeapp.generated.resources.title_products
import qhatuapp.composeapp.generated.resources.title_sales
import qhatuapp.composeapp.generated.resources.title_search
import qhatuapp.composeapp.generated.resources.title_setting

@Composable
fun TopBarApp(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val shoppingToolbarRoutes: Map<String?, String> = mapOf(
        SearchDestination::class.qualifiedName to stringResource(Res.string.title_search)
    )

    when (currentRoute) {
        SettingDestination::class.qualifiedName -> {
            QhatuToolbar(
                title = stringResource(Res.string.title_setting),
                onNavigationClick = { navController.popBackStack() },
                actions = {}
            )
        }

        PaymentDestination::class.qualifiedName -> {
            QhatuToolbar(
                title = stringResource(Res.string.title_payments),
                onNavigationClick = { navController.popBackStack() },
                actions = {}
            )
        }

        CustomerDestination::class.qualifiedName -> {
            QhatuToolbar(
                title = stringResource(Res.string.title_customers),
                onNavigationClick = { navController.popBackStack() },
                actions = {}
            )
        }

        ProductDestination::class.qualifiedName -> {
            QhatuToolbar(
                title = stringResource(Res.string.title_products),
                onNavigationClick = { navController.popBackStack() },
                actions = {}
            )
        }

        SaleDestination::class.qualifiedName -> {
            QhatuToolbar(
                title = stringResource(Res.string.title_sales),
                onNavigationClick = { navController.popBackStack() },
                actions = {}
            )
        }

        in shoppingToolbarRoutes.keys -> {
            QhatuCartToolbar(
                title = shoppingToolbarRoutes[currentRoute] ?: "",
                cartValue = 10.0,
                cartItemCount = 100,
                onNavigationClick = { navController.popBackStack() },
                onCartClick = { /* Acción de ir al carrito */ }
            )
        }
    }
}
