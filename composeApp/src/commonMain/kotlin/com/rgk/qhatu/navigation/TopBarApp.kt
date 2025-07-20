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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rgk.qhatu.common.components.toolbar.QhatuCartToolbar
import com.rgk.qhatu.common.components.toolbar.QhatuToolbar
import com.rgk.qhatu.feature.payment.presentation.payment.PaymentDestination
import com.rgk.qhatu.feature.search.presentation.search.SearchDestination
import com.rgk.qhatu.feature.setting.presentation.category.CategoryDestination
import com.rgk.qhatu.feature.setting.presentation.setting.SettingDestination

@Composable
fun TopBarApp(
    navController: NavController,
) {
    val destinationsWithToolbar = listOf(
        SettingDestination::class.qualifiedName,
        CategoryDestination::class.qualifiedName,
        PaymentDestination::class.qualifiedName,
    )
    val cartToolbarDestinations = listOf(
        SearchDestination::class.qualifiedName,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    navBackStackEntry?.let { entry ->
        val currentRoute = navBackStackEntry?.destination?.route
        if (currentRoute in destinationsWithToolbar || currentRoute in cartToolbarDestinations) {
            val viewModel: TopAppBarViewModel = viewModel(
                viewModelStoreOwner = entry,
                initializer = { TopAppBarViewModel() },
            )
            when (currentRoute) {
                in cartToolbarDestinations -> {
                    QhatuCartToolbar(
                        title = viewModel.title,
                        navigationIcon = viewModel.navigationIcon,
                        actions = viewModel.actions,
                    )
                }

                in destinationsWithToolbar -> {
                    QhatuToolbar(
                        title = viewModel.title,
                        navigationIcon = viewModel.navigationIcon,
                        actions = viewModel.actions,
                    )
                }
            }
        }
    }
}

@Composable
fun ProvideAppBarTitle(title: @Composable () -> Unit) {

    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    (viewModelStoreOwner as? NavBackStackEntry)?.let { owner ->
        val viewModel: TopAppBarViewModel = viewModel(
            viewModelStoreOwner = owner,
            initializer = { TopAppBarViewModel() },
        )
        LaunchedEffect(title) {
            viewModel.title = title
        }
    }

}

@Composable
fun ProvideAppBarNavigationIcon(navigationIcon: @Composable () -> Unit) {

    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    (viewModelStoreOwner as? NavBackStackEntry)?.let { owner ->
        val viewModel: TopAppBarViewModel = viewModel(
            viewModelStoreOwner = owner,
            initializer = { TopAppBarViewModel() },
        )
        LaunchedEffect(navigationIcon) {
            viewModel.navigationIcon = navigationIcon
        }
    }

}

@Composable
fun ProvideAppBarActions(actions: @Composable RowScope.() -> Unit) {

    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    (viewModelStoreOwner as? NavBackStackEntry)?.let { owner ->
        val viewModel: TopAppBarViewModel = viewModel(
            viewModelStoreOwner = owner,
            initializer = { TopAppBarViewModel() },
        )
        LaunchedEffect(actions) {
            viewModel.actions = actions
        }
    }

}

private class TopAppBarViewModel : ViewModel() {
    var title by mutableStateOf<@Composable () -> Unit>({ }, referentialEqualityPolicy())

    var navigationIcon by mutableStateOf<@Composable () -> Unit>({ }, referentialEqualityPolicy())

    var actions by mutableStateOf<@Composable RowScope.() -> Unit>({ }, referentialEqualityPolicy())
}