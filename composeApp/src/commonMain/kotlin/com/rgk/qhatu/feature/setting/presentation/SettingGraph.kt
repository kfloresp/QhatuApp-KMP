package com.rgk.qhatu.feature.setting.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.feature.setting.presentation.setting.SettingDestination
import com.rgk.qhatu.feature.setting.presentation.setting.settingDestination
import com.rgk.qhatu.feature.setting.presentation.setting.component.SettingType
import kotlinx.serialization.Serializable

@Serializable
data object SettingGraph

fun NavController.navigateToSetting(navOptions: NavOptions? = null) {
    navigate(SettingGraph, navOptions)
}

fun NavGraphBuilder.settingGraph(
    onOptionClick: (SettingType) -> Unit
) {
    navigation<SettingGraph>(
        startDestination = SettingDestination
    ) {
        settingDestination(onOptionClick = onOptionClick)
    }
}