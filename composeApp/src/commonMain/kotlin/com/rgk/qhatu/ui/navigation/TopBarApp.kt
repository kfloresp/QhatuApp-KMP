package com.rgk.qhatu.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rgk.qhatu.ui.components.toolbar.QhatuCartToolbar
import com.rgk.qhatu.ui.components.toolbar.QhatuToolbar
import com.rgk.qhatu.ui.feature.home.navigation.HomeDestination
import com.rgk.qhatu.ui.feature.search.navigation.SearchDestination
import com.rgk.qhatu.ui.feature.settings.navigation.SettingDestination
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.title_search
import qhatuapp.composeapp.generated.resources.title_setting

@Composable
fun TopBarApp(
    navController: NavController,
)  {
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
