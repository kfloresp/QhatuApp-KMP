package com.rgk.qhatu.feature.setting.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.common.extension.navigateToAuthGraphWithPopUp
import com.rgk.qhatu.feature.auth.presentation.navigateToAuthGraph
import com.rgk.qhatu.feature.setting.presentation.brand.BrandDestination
import com.rgk.qhatu.feature.setting.presentation.brand.brandDestination
import com.rgk.qhatu.feature.setting.presentation.category.CategoryDestination
import com.rgk.qhatu.feature.setting.presentation.category.categoryDestination
import com.rgk.qhatu.feature.setting.presentation.setting.SettingDestination
import com.rgk.qhatu.feature.setting.presentation.setting.settingDestination
import com.rgk.qhatu.feature.setting.presentation.setting.component.SettingType
import com.rgk.qhatu.feature.setting.presentation.unitmeasure.UnitMeasureDestination
import com.rgk.qhatu.feature.setting.presentation.unitmeasure.unitMeasureDestination
import kotlinx.serialization.Serializable

@Serializable
data object SettingGraph

fun NavController.navigateToSettingGraph(navOptions: NavOptions? = null) {
    navigate(SettingGraph, navOptions)
}

fun NavGraphBuilder.settingGraph(
    navController: NavController,
    closeSession: () -> Unit,
) {
    navigation<SettingGraph>(
        startDestination = SettingDestination
    ) {
        settingDestination(
            onProfileClick = {

            },
            onCategoriesClick = {
                navController.navigate(CategoryDestination)
            },
            onBrandsClick = {
                navController.navigate(BrandDestination)
            },
            onUnitsClick = {
                navController.navigate(UnitMeasureDestination)
            },
            onSyncDataClick = {

            },
            onExportDataClick = {

            },
            onLogoutClick = {
                closeSession()
            },
        )
        unitMeasureDestination()
        brandDestination()
        categoryDestination()
    }
}