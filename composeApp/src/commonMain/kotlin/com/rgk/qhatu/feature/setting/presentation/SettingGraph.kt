package com.rgk.qhatu.feature.setting.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.feature.setting.presentation.category.CategoryDestination
import com.rgk.qhatu.feature.setting.presentation.category.categoryDestination
import com.rgk.qhatu.feature.setting.presentation.setting.SettingDestination
import com.rgk.qhatu.feature.setting.presentation.setting.settingDestination
import com.rgk.qhatu.feature.setting.presentation.setting.component.SettingType
import kotlinx.serialization.Serializable

@Serializable
data object SettingGraph

fun NavController.navigateToSettingGraph(navOptions: NavOptions? = null) {
    navigate(SettingGraph, navOptions)
}

fun NavGraphBuilder.settingGraph(
    navController: NavController,
) {
    navigation<SettingGraph>(
        startDestination = SettingDestination
    ) {
        settingDestination(
            onOptionClick = { settingType ->
                when (settingType) {
                    SettingType.PROFILE -> {}
                    SettingType.CATEGORIES -> {
                        navController.navigate(CategoryDestination)
                    }
                    SettingType.BRANDS -> {}
                    SettingType.UNITS -> {}
                    SettingType.SYNC_DATA -> {}
                    SettingType.EXPORT_DATA -> {}
                    SettingType.LOGOUT -> {

                    }
                }
            }
        )
        categoryDestination()
    }
}