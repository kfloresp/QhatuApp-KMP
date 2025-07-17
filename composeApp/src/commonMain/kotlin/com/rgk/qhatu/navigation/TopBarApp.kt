package com.rgk.qhatu.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rgk.qhatu.common.components.toolbar.QhatuCartToolbar
import com.rgk.qhatu.common.components.toolbar.QhatuToolbar
import com.rgk.qhatu.common.extension.getViewModelIfRouteMatches
import com.rgk.qhatu.feature.customer.presentation.customer.CustomerDestination
import com.rgk.qhatu.feature.payment.presentation.payment.PaymentDestination
import com.rgk.qhatu.feature.product.presentation.product.ProductDestination
import com.rgk.qhatu.feature.sale.presentation.sale.SaleDestination
import com.rgk.qhatu.feature.search.presentation.search.SearchDestination
import com.rgk.qhatu.feature.setting.presentation.category.CategoryDestination
import com.rgk.qhatu.feature.setting.presentation.category.CategoryViewModel
import com.rgk.qhatu.feature.setting.presentation.setting.SettingDestination
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.title_customers
import qhatuapp.composeapp.generated.resources.title_payments
import qhatuapp.composeapp.generated.resources.title_products
import qhatuapp.composeapp.generated.resources.title_sales
import qhatuapp.composeapp.generated.resources.title_search
import qhatuapp.composeapp.generated.resources.title_setting
import qhatuapp.composeapp.generated.resources.tx_categories_title

@Composable
fun TopBarApp(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val shoppingToolbarRoutes: Map<String?, String> = mapOf(
        SearchDestination::class.qualifiedName to stringResource(Res.string.title_search)
    )

    val categoryViewModel = getViewModelIfRouteMatches<CategoryViewModel>(
        currentRoute = currentRoute,
        expectedRoute = CategoryDestination::class.qualifiedName ?: ""
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

        CategoryDestination::class.qualifiedName -> {
            QhatuToolbar(
                title = stringResource(Res.string.tx_categories_title),
                onNavigationClick = { navController.popBackStack() },
            ){
                IconButton(onClick = {
                    categoryViewModel?.syncCategories()
                }) {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = null
                    )
                }
            }
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
