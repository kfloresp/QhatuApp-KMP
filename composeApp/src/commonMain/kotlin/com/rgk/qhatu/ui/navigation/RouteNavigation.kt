package com.rgk.qhatu.ui.navigation

sealed class RouteNavigation(val src: String) {
    data object Splash : RouteNavigation("splash")
    data object Home : RouteNavigation("home")
    data object Login : RouteNavigation("login")
    data object Search : RouteNavigation("search")
    data object Movement : RouteNavigation("movement")
    data object Sale : RouteNavigation("sale")
    data object Profile : RouteNavigation("profile")
    data object Setting : RouteNavigation("setting")
    data object SearchProvider : RouteNavigation("search-provider/{query}"){
        object Args {
            const val QUERY = "query"
            const val SELECTED_PROVIDER_ID = "selected_provider_id"
        }
        fun createRoute(query: String) = "search-provider/$query"
    }
    data object Sync : RouteNavigation("sync")
    data object Receipt : RouteNavigation("receipt")
    data object ReceiptDetail : RouteNavigation("receipt-detail")
}