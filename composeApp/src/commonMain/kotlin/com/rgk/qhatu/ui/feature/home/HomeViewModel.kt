package com.rgk.qhatu.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.rgk.qhatu.ui.navigation.RouteNavigation

class HomeViewModel: ViewModel() {

    fun onMenuItemClick(item: HomeItem, navController: NavController) {
        when (item) {
            is HomeItem.Logout -> {
                navController.navigate(item.routeNavigation) {
                    popUpTo(RouteNavigation.Login.src){
                        inclusive = true
                    }
                }
            }
            else -> {
                navController.navigate(item.routeNavigation) {
                   popUpTo(RouteNavigation.Home.src)
                }
            }
        }
    }

    fun getMenuItems(): List<HomeItem> = HomeItem.allItems
}