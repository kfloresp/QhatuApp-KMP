package com.rgk.qhatu.ui.feature.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Sync
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.rgk.qhatu.ui.navigation.RouteNavigation
import org.jetbrains.compose.resources.StringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.title_advanced_search
import qhatuapp.composeapp.generated.resources.title_setting
import qhatuapp.composeapp.generated.resources.title_exit
import qhatuapp.composeapp.generated.resources.title_movement
import qhatuapp.composeapp.generated.resources.title_profile
import qhatuapp.composeapp.generated.resources.title_receipt
import qhatuapp.composeapp.generated.resources.title_sale
import qhatuapp.composeapp.generated.resources.title_search
import qhatuapp.composeapp.generated.resources.title_sync

sealed class HomeItem(
    val title: StringResource,
    val icon: ImageVector,
    val color: Color,
    val routeNavigation: String
) {
    data object Search : HomeItem(
        Res.string.title_search,
        Icons.Default.Search,
        Color(0xFF4CAF50),
        RouteNavigation.Search.src
    ){
        data object AdvancedSearch : HomeItem(
            Res.string.title_advanced_search,
            Icons.Default.Search,
            Color(0xFF4CAF50),
            RouteNavigation.AdvancedSearch.src)
    }

    data object Movement : HomeItem(
        Res.string.title_movement,
        Icons.Default.History,
        Color(0xFF9C27B0),
        RouteNavigation.Movement.src
    )

    data object Receipt :
        HomeItem(
            Res.string.title_receipt,
            Icons.Default.Receipt,
            Color(0xFFF44336),
            RouteNavigation.Receipt.src
        )

    data object Sale :
        HomeItem(
            Res.string.title_sale,
            Icons.Default.PointOfSale,
            Color(0xFFFF9800),
            RouteNavigation.Sale.src
        )

    data object Profile :
        HomeItem(
            Res.string.title_profile,
            Icons.Default.Person,
            Color(0xFF607D8B),
            RouteNavigation.Profile.src
        )

    data object Sync :
        HomeItem(
            Res.string.title_sync,
            Icons.Default.Sync,
            Color(0xFF2196F3),
            RouteNavigation.Sync.src
        )

    data object Setting : HomeItem(
        Res.string.title_setting,
        Icons.Default.Settings,
        Color(0xFF5C6BC0),
        RouteNavigation.Setting.src
    )

    data object Logout : HomeItem(
        Res.string.title_exit,
        Icons.AutoMirrored.Filled.Logout,
        Color(0xFF795548),
        RouteNavigation.Login.src
    )

    companion object {
        val allItems = listOf(Search, Movement, Receipt, Sale, Profile, Sync, Setting, Logout)
    }
}
