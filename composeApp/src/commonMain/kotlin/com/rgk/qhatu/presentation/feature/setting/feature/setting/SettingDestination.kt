package com.rgk.qhatu.presentation.feature.setting.feature.setting
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.presentation.feature.setting.feature.setting.component.SettingType
import kotlinx.serialization.Serializable

@Serializable
data object SettingDestination

internal fun NavGraphBuilder.settingDestination(
    onOptionClick: (SettingType) -> Unit
) {
    composable<SettingDestination> {
        SettingScreen(
            onOptionClick = onOptionClick
        )
    }
}