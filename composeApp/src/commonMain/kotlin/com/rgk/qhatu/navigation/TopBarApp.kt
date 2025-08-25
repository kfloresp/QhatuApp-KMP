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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rgk.qhatu.common.components.toolbar.QhatuToolbar
import com.rgk.qhatu.feature.auth.presentation.auth.AuthDestination
import com.rgk.qhatu.feature.cart.presentation.cart.CartDestination
import com.rgk.qhatu.feature.cart.presentation.checkout.CheckoutDestination
import com.rgk.qhatu.feature.customer.presentation.customer.CustomerDestination
import com.rgk.qhatu.feature.customer.presentation.customerform.CustomerFormDestination
import com.rgk.qhatu.feature.customer.presentation.customerprofile.CustomerProfileDestination
import com.rgk.qhatu.feature.customer.presentation.customersummary.CustomerSummaryDestination
import com.rgk.qhatu.feature.payment.presentation.payment.PaymentDestination
import com.rgk.qhatu.feature.payment.presentation.paymentform.PaymentFormDestination
import com.rgk.qhatu.feature.product.presentation.product.ProductDestination
import com.rgk.qhatu.feature.product.presentation.productform.ProductFormDestination
import com.rgk.qhatu.feature.sale.presentation.sale.SaleDestination
import com.rgk.qhatu.feature.search.presentation.search.SearchDestination
import com.rgk.qhatu.feature.setting.presentation.brand.BrandDestination
import com.rgk.qhatu.feature.setting.presentation.category.CategoryDestination
import com.rgk.qhatu.feature.setting.presentation.setting.SettingDestination
import com.rgk.qhatu.feature.setting.presentation.store.StoreDestination
import com.rgk.qhatu.feature.setting.presentation.sync.SyncDestination
import com.rgk.qhatu.feature.setting.presentation.unitmeasure.UnitMeasureDestination
import com.rgk.qhatu.feature.splash.presentation.splash.SplashDestination
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.title_search
import qhatuapp.composeapp.generated.resources.title_setting
import qhatuapp.composeapp.generated.resources.tx_brands_title
import qhatuapp.composeapp.generated.resources.tx_cart_title
import qhatuapp.composeapp.generated.resources.tx_categories_title
import qhatuapp.composeapp.generated.resources.tx_checkout_title
import qhatuapp.composeapp.generated.resources.tx_customer_profile_title
import qhatuapp.composeapp.generated.resources.tx_customer_resume_summary_title
import qhatuapp.composeapp.generated.resources.tx_customer_title
import qhatuapp.composeapp.generated.resources.tx_payment_customer_title
import qhatuapp.composeapp.generated.resources.tx_product_title
import qhatuapp.composeapp.generated.resources.tx_profile_title
import qhatuapp.composeapp.generated.resources.tx_sales_title
import qhatuapp.composeapp.generated.resources.tx_sync_title
import qhatuapp.composeapp.generated.resources.tx_units_title

private val allDestinations = mapOf(
    SettingDestination::class.qualifiedName to Res.string.title_setting,
    CategoryDestination::class.qualifiedName to Res.string.tx_categories_title,
    BrandDestination::class.qualifiedName to Res.string.tx_brands_title,
    UnitMeasureDestination::class.qualifiedName to Res.string.tx_units_title,
    StoreDestination::class.qualifiedName to Res.string.tx_profile_title,
    SyncDestination::class.qualifiedName to Res.string.tx_sync_title,
    CustomerDestination::class.qualifiedName to Res.string.tx_customer_title,
    CustomerProfileDestination::class.qualifiedName to Res.string.tx_customer_profile_title,
    CustomerFormDestination::class.qualifiedName to Res.string.tx_customer_title,
    CustomerSummaryDestination::class.qualifiedName to Res.string.tx_customer_resume_summary_title,
    PaymentDestination::class.qualifiedName to Res.string.tx_payment_customer_title,
    PaymentFormDestination::class.qualifiedName to Res.string.tx_payment_customer_title,
    ProductDestination::class.qualifiedName to Res.string.tx_product_title,
    ProductFormDestination::class.qualifiedName to Res.string.tx_product_title,
    CartDestination::class.qualifiedName to Res.string.tx_cart_title,
    SaleDestination::class.qualifiedName to Res.string.tx_sales_title,
    SearchDestination::class.qualifiedName to Res.string.title_search,
    CheckoutDestination::class.qualifiedName to Res.string.tx_checkout_title,
)
val routesWithoutTopBar = listOf(
    SplashDestination::class,
    AuthDestination::class
)

@Composable
fun TopBarApp(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    navBackStackEntry?.let { entry ->

        val currentRoute = entry.destination

        val viewModel: TopBarAppViewModel = viewModel(
            viewModelStoreOwner = entry,
            initializer = { TopBarAppViewModel() },
        )
        val shouldShowTopBar = routesWithoutTopBar.none { currentRoute.hasRoute(it) }
        if (shouldShowTopBar) {
            QhatuToolbar(
                title = viewModel.title ?: getTitleDestination(currentRoute.route),
                showAppIcon = viewModel.showAppIcon,
                showBackNavigation = viewModel.showBackNavigation,
                onBackNavigationClick = {
                    viewModel.onBackStack?.let {
                        it()
                    } ?: run {
                        navController.popBackStack()
                    }
                },
                actions = viewModel.actions,
            )
        }
    }
}

@Composable
private fun getTitleDestination(currentRoute: String?): String {
    val resId = allDestinations[currentRoute]
    return resId?.let { stringResource(it) } ?: ""
}

@Composable
fun ProvideAppBar(
    actions: (@Composable RowScope.() -> Unit) = { },
    title: String? = null,
    onBackStack: (() -> Unit)? = null,
    showAppIcon: Boolean = false,
    showBackNavigation: Boolean = true,
) {
    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    (viewModelStoreOwner as? NavBackStackEntry)?.let { owner ->
        val viewModel: TopBarAppViewModel = viewModel(
            viewModelStoreOwner = owner,
            initializer = { TopBarAppViewModel() },
        )
        LaunchedEffect(actions, title, onBackStack, showAppIcon, showBackNavigation) {
            viewModel.actions = actions
            viewModel.title = title
            viewModel.onBackStack = onBackStack
            viewModel.showAppIcon = showAppIcon
            viewModel.showBackNavigation = showBackNavigation
        }
    }
}


private class TopBarAppViewModel : ViewModel() {
    var actions by mutableStateOf<@Composable RowScope.() -> Unit>({ }, referentialEqualityPolicy())
    var title by mutableStateOf<String?>(null, referentialEqualityPolicy())
    var showAppIcon by mutableStateOf(false, referentialEqualityPolicy())
    var showBackNavigation by mutableStateOf(true, referentialEqualityPolicy())
    var onBackStack by mutableStateOf<(() -> Unit)?>(null, referentialEqualityPolicy())
}