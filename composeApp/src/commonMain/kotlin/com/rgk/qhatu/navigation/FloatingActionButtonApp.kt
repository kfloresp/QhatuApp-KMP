package com.rgk.qhatu.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rgk.qhatu.feature.customer.presentation.customer.CustomerDestination
import com.rgk.qhatu.feature.setting.presentation.brand.BrandDestination
import com.rgk.qhatu.feature.setting.presentation.category.CategoryDestination
import com.rgk.qhatu.feature.setting.presentation.unitmeasure.UnitMeasureDestination

private val destinationsWithFab = listOf(
    CategoryDestination::class.qualifiedName,
    BrandDestination::class.qualifiedName,
    UnitMeasureDestination::class.qualifiedName,
    CustomerDestination::class.qualifiedName,
)

@Composable
fun FloatingActionButtonApp(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?.substringBefore("/")

    if (currentRoute in destinationsWithFab) {
        val viewModel: FabViewModel = viewModel(
            viewModelStoreOwner = navBackStackEntry!!,
            initializer = { FabViewModel() }
        )
        when (currentRoute) {
            in destinationsWithFab -> {
                viewModel.fabContent()
            }
        }
    }
}

@Composable
fun ProvideFabAction(content: @Composable () -> Unit) {
    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    (viewModelStoreOwner as? NavBackStackEntry)?.let { owner ->
        val viewModel: FabViewModel = viewModel(
            viewModelStoreOwner = owner,
            initializer = { FabViewModel() },
        )
        LaunchedEffect(content) {
            viewModel.fabContent = content
        }
    }
}

class FabViewModel : ViewModel() {
    var fabContent: @Composable () -> Unit? by mutableStateOf({})

}