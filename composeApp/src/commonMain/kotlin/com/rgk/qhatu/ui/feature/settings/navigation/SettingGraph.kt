package com.rgk.qhatu.ui.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

@Serializable
data object SettingGraph

fun NavController.navigateToSetting(navOptions: NavOptions? = null) {
    navigate(SettingGraph, navOptions)
}

fun NavGraphBuilder.settingGraph(
) {
    navigation<SettingGraph>(
        startDestination = SettingDestination
    ) {
        settingDestination()
    }
}