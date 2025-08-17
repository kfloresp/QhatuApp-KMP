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
import com.rgk.qhatu.feature.customer.presentation.customer.CustomerDestination
import com.rgk.qhatu.feature.customer.presentation.customerform.CustomerFormDestination
import com.rgk.qhatu.feature.customer.presentation.customerprofile.CustomerProfileDestination
import com.rgk.qhatu.feature.customer.presentation.customersummary.CustomerSummaryDestination
import com.rgk.qhatu.feature.payment.presentation.payment.PaymentDestination
import com.rgk.qhatu.feature.payment.presentation.paymentform.PaymentFormDestination
import com.rgk.qhatu.feature.product.presentation.product.ProductDestination
import com.rgk.qhatu.feature.product.presentation.productform.ProductFormDestination
import com.rgk.qhatu.feature.search.presentation.search.SearchDestination
import com.rgk.qhatu.feature.setting.presentation.brand.BrandDestination
import com.rgk.qhatu.feature.setting.presentation.category.CategoryDestination
import com.rgk.qhatu.feature.setting.presentation.setting.SettingDestination
import com.rgk.qhatu.feature.setting.presentation.store.StoreDestination
import com.rgk.qhatu.feature.setting.presentation.sync.SyncDestination
import com.rgk.qhatu.feature.setting.presentation.unitmeasure.UnitMeasureDestination
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.title_search
import qhatuapp.composeapp.generated.resources.title_setting
import qhatuapp.composeapp.generated.resources.tx_brands_title
import qhatuapp.composeapp.generated.resources.tx_categories_title
import qhatuapp.composeapp.generated.resources.tx_customer_profile_title
import qhatuapp.composeapp.generated.resources.tx_customer_resume_summary_title
import qhatuapp.composeapp.generated.resources.tx_customer_title
import qhatuapp.composeapp.generated.resources.tx_payment_customer_title
import qhatuapp.composeapp.generated.resources.tx_product_title
import qhatuapp.composeapp.generated.resources.tx_profile_title
import qhatuapp.composeapp.generated.resources.tx_sync_title
import qhatuapp.composeapp.generated.resources.tx_units_title

private val destinationsWithToolbar = mapOf(
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
)
private val cartToolbarDestinations = mapOf(
    SearchDestination::class.qualifiedName to Res.string.title_search
)

private val allDestinations = destinationsWithToolbar + cartToolbarDestinations

@Composable
fun TopBarApp(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    navBackStackEntry?.let { entry ->
        val currentRoute = navBackStackEntry?.destination?.route?.substringBefore("/")
        val titleDestination = getTitleDestination(currentRoute)
        if (currentRoute in allDestinations) {
            val viewModel: TopAppBarViewModel = viewModel(
                viewModelStoreOwner = entry,
                initializer = { TopAppBarViewModel() },
            )
            when (currentRoute) {
                in cartToolbarDestinations -> {
                    QhatuCartToolbar(
                        title = viewModel.title ?: titleDestination,
                        onBackClick = {
                            viewModel.onBackStack?.invoke() ?: navController.popBackStack()
                        },
                        actions = viewModel.actions,
                    )
                }

                in destinationsWithToolbar -> {
                    QhatuToolbar(
                        title = viewModel.title ?: titleDestination,
                        onBackClick = {
                            viewModel.onBackStack?.invoke() ?: navController.popBackStack()
                        },
                        actions = viewModel.actions,
                    )
                }
            }
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
) {
    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    (viewModelStoreOwner as? NavBackStackEntry)?.let { owner ->
        val viewModel: TopAppBarViewModel = viewModel(
            viewModelStoreOwner = owner,
            initializer = { TopAppBarViewModel() },
        )
        LaunchedEffect(actions, title, onBackStack) {
            viewModel.actions = actions
            viewModel.title = title
            viewModel.onBackStack = onBackStack
        }
    }
}


private class TopAppBarViewModel : ViewModel() {
    var actions by mutableStateOf<@Composable RowScope.() -> Unit>({ }, referentialEqualityPolicy())
    var title by mutableStateOf<String?>(null, referentialEqualityPolicy())
    var onBackStack by mutableStateOf<(() -> Unit)?>(null, referentialEqualityPolicy())
}