package com.rgk.qhatu.ui.feature.settings.presentation.settings
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.ui.feature.settings.presentation.settings.component.SettingType
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