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
    data object AdvancedSearch : RouteNavigation("advanced-search/{query}/{searchType}"){
        object Args {
            const val Query = "query"
            const val SearchType = "searchType"
        }
        fun createRoute(query: String, searchType: Int) = "advanced-search/$query/$searchType"
    }
    data object Sync : RouteNavigation("sync")
    data object Receipt : RouteNavigation("receipt")
    data object ReceiptDetail : RouteNavigation("receipt/{id}") {
        fun createRoute(id: String) = "receipt/$id"
    }
}