package com.rgk.qhatu.ui.feature.settings.navigation
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.ui.feature.settings.SettingScreen
import kotlinx.serialization.Serializable

@Serializable
data object SettingDestination

internal fun NavGraphBuilder.settingDestination(
) {
    composable<SettingDestination> {
        SettingScreen(
        )
    }
}