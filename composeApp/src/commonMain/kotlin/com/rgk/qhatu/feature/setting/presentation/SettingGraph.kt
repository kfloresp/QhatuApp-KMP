package com.rgk.qhatu.feature.setting.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.feature.setting.presentation.brand.BrandDestination
import com.rgk.qhatu.feature.setting.presentation.brand.brandDestination
import com.rgk.qhatu.feature.setting.presentation.category.CategoryDestination
import com.rgk.qhatu.feature.setting.presentation.category.categoryDestination
import com.rgk.qhatu.feature.setting.presentation.setting.SettingDestination
import com.rgk.qhatu.feature.setting.presentation.setting.settingDestination
import com.rgk.qhatu.feature.setting.presentation.store.StoreDestination
import com.rgk.qhatu.feature.setting.presentation.store.storeDestination
import com.rgk.qhatu.feature.setting.presentation.sync.SyncDestination
import com.rgk.qhatu.feature.setting.presentation.sync.syncDestination
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
            onStoreClick = {
                navController.navigate(StoreDestination)
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
                navController.navigate(SyncDestination)
            },
            onExportDataClick = {},
            onLogoutClick = {
                closeSession()
            },
        )
        unitMeasureDestination()
        brandDestination()
        categoryDestination()
        storeDestination()
        syncDestination()
    }
}