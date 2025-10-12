package com.rgk.qhatu.common.components.bar

import com.rgk.qhatu.feature.customer.presentation.CustomerGraph
import com.rgk.qhatu.feature.home.presentation.HomeGraph
import com.rgk.qhatu.feature.sale.presentation.SaleGraph
import com.rgk.qhatu.feature.search.presentation.SearchGraph
import com.rgk.qhatu.feature.setting.presentation.SettingGraph
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.ic_outline_more_horiz_24
import qhatuapp.composeapp.generated.resources.ic_round_home_24
import qhatuapp.composeapp.generated.resources.ic_round_person_24
import qhatuapp.composeapp.generated.resources.ic_rounded_home_24
import qhatuapp.composeapp.generated.resources.ic_rounded_person_24
import qhatuapp.composeapp.generated.resources.ic_rounded_search_24
import qhatuapp.composeapp.generated.resources.ic_rounded_search_activity_24
import qhatuapp.composeapp.generated.resources.tx_menu_customer
import qhatuapp.composeapp.generated.resources.tx_menu_main
import qhatuapp.composeapp.generated.resources.tx_menu_sales
import qhatuapp.composeapp.generated.resources.tx_menu_search
import qhatuapp.composeapp.generated.resources.tx_menu_setting

data class BottomBarItem(
    val titleRes: StringResource,
    val selectedIconRes: DrawableResource,
    val unselectedIconRes: DrawableResource,
    val graph: Any
)

val bottomBarItems = listOf(
    BottomBarItem(
        titleRes = Res.string.tx_menu_main,
        selectedIconRes = Res.drawable.ic_round_home_24,
        unselectedIconRes = Res.drawable.ic_rounded_home_24,
        graph = HomeGraph,
    ),
    BottomBarItem(
        titleRes = Res.string.tx_menu_sales,
        selectedIconRes = Res.drawable.ic_rounded_search_activity_24,
        unselectedIconRes = Res.drawable.ic_rounded_search_activity_24,
        graph = SaleGraph,
    ),
    BottomBarItem(
        titleRes = Res.string.tx_menu_search,
        selectedIconRes = Res.drawable.ic_rounded_search_24,
        unselectedIconRes = Res.drawable.ic_rounded_search_24,
        graph = SearchGraph,
    ),
    BottomBarItem(
        titleRes = Res.string.tx_menu_customer,
        selectedIconRes = Res.drawable.ic_round_person_24,
        unselectedIconRes = Res.drawable.ic_rounded_person_24,
        graph = CustomerGraph,
    ),
    BottomBarItem(
        titleRes = Res.string.tx_menu_setting,
        selectedIconRes = Res.drawable.ic_outline_more_horiz_24,
        unselectedIconRes = Res.drawable.ic_outline_more_horiz_24,
        graph = SettingGraph,
    ),
)