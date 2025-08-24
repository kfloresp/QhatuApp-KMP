package com.rgk.qhatu.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rgk.qhatu.common.components.bottombar.QhatuBottombar
import com.rgk.qhatu.feature.auth.presentation.auth.AuthDestination
import com.rgk.qhatu.feature.cart.presentation.cart.CartDestination
import com.rgk.qhatu.feature.splash.presentation.splash.SplashDestination
import kotlinx.serialization.Serializable

val routesWithTopBar = listOf(
    CartDestination::class,
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
        val shouldShowTopBar = routesWithTopBar.any { currentRoute.hasRoute(it) }
        if (shouldShowTopBar) {
            QhatuBottombar(content = viewModel.actions)
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