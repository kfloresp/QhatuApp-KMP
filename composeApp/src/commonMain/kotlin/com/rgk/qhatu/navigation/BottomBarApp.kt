package com.rgk.qhatu.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rgk.qhatu.common.components.bar.QhatuBottomNavigationBar
import com.rgk.qhatu.common.components.bar.UnderlinedNavigationBarItem
import com.rgk.qhatu.common.components.bar.bottomBarItems
import com.rgk.qhatu.common.components.bottombar.QhatuBottombar
import com.rgk.qhatu.feature.cart.presentation.cart.CartDestination
import com.rgk.qhatu.feature.cart.presentation.checkout.CheckoutDestination
import com.rgk.qhatu.feature.customer.presentation.customer.CustomerDestination
import com.rgk.qhatu.feature.home.presentation.home.HomeDestination
import com.rgk.qhatu.feature.sale.presentation.sale.SaleDestination
import com.rgk.qhatu.feature.search.presentation.search.SearchDestination
import com.rgk.qhatu.feature.setting.presentation.setting.SettingDestination
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource

@Serializable
object NoneGraph

val routesWithBottomBar = listOf(
    CartDestination::class,
    CheckoutDestination::class,
)

val routesWithMainBottomBar = listOf(
    HomeDestination::class,
    SaleDestination::class,
    SearchDestination::class,
    CustomerDestination::class,
    SettingDestination::class,
)

@Composable
fun BottomBarApp(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    navBackStackEntry?.let { entry ->

        val currentRoute = entry.destination

        val viewModel: BottomBarAppViewModel = viewModel(
            viewModelStoreOwner = entry,
            initializer = { BottomBarAppViewModel() },
        )
        when {
            routesWithMainBottomBar.any { currentRoute.hasRoute(it) } -> {
                QhatuBottomNavigationBar {
                    bottomBarItems.forEachIndexed { _, bottomBarItem ->
                        val isSelected = currentRoute.hierarchy.any {
                            it.hasRoute(route = bottomBarItem.graph::class)
                        }

                        this@QhatuBottomNavigationBar.UnderlinedNavigationBarItem(
                            title = stringResource(bottomBarItem.titleRes),
                            selected = isSelected,
                            onClick = {
                                val isInCurrentGraph = currentRoute.hierarchy.any {
                                    it.hasRoute(route = bottomBarItem.graph::class)
                                }
                                if (bottomBarItem.graph != NoneGraph && !isInCurrentGraph) {
                                    navController.navigate(route = bottomBarItem.graph) {
                                        popUpTo(0) { inclusive = true }
                                        launchSingleTop = true
                                    }
                                }
                            },
                            iconRes = if (isSelected) bottomBarItem.selectedIconRes else bottomBarItem.unselectedIconRes,
                            contentDescription = stringResource(bottomBarItem.titleRes),
                        )
                    }
                }
            }

            routesWithBottomBar.any { currentRoute.hasRoute(it) } -> {
                QhatuBottombar(content = viewModel.actions)
            }
        }
    }
}

@Composable
fun ProvideBottomBarApp(
    actions: (@Composable RowScope.() -> Unit) = { },
) {
    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    (viewModelStoreOwner as? NavBackStackEntry)?.let { owner ->
        val viewModel: BottomBarAppViewModel = viewModel(
            viewModelStoreOwner = owner,
            initializer = { BottomBarAppViewModel() },
        )
        LaunchedEffect(actions) {
            viewModel.actions = actions
        }
    }
}

private class BottomBarAppViewModel : ViewModel() {
    var actions by mutableStateOf<@Composable RowScope.() -> Unit>({ }, referentialEqualityPolicy())
}