package com.rgk.qhatu.feature.setting.presentation.setting
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.feature.setting.presentation.setting.component.SettingType
import com.rgk.qhatu.navigation.ProvideAppBarNavigationIcon
import com.rgk.qhatu.navigation.ProvideAppBarTitle
import kotlinx.serialization.Serializable

@Serializable
data object SettingDestination

internal fun NavGraphBuilder.settingDestination(
    onOptionClick: (SettingType) -> Unit,
    onBackClick: () -> Unit,
) {

    composable<SettingDestination> {
        ProvideAppBarTitle {
            Text("Settings")
        }
        ProvideAppBarNavigationIcon {
            IconButton(
                onClick = {
                    onBackClick()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
        SettingScreen(
            onOptionClick = onOptionClick
        )
    }
}