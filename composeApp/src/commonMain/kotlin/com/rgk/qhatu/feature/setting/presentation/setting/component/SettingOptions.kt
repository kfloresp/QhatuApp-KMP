package com.rgk.qhatu.feature.setting.presentation.setting.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.NewLabel
import androidx.compose.material.icons.outlined.SquareFoot
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_brands_subtitle
import qhatuapp.composeapp.generated.resources.tx_brands_title
import qhatuapp.composeapp.generated.resources.tx_categories_subtitle
import qhatuapp.composeapp.generated.resources.tx_categories_title
import qhatuapp.composeapp.generated.resources.tx_export_subtitle
import qhatuapp.composeapp.generated.resources.tx_export_title
import qhatuapp.composeapp.generated.resources.tx_logout_subtitle
import qhatuapp.composeapp.generated.resources.tx_logout_title
import qhatuapp.composeapp.generated.resources.tx_profile_subtitle
import qhatuapp.composeapp.generated.resources.tx_profile_title
import qhatuapp.composeapp.generated.resources.tx_sync_subtitle
import qhatuapp.composeapp.generated.resources.tx_sync_title
import qhatuapp.composeapp.generated.resources.tx_units_subtitle
import qhatuapp.composeapp.generated.resources.tx_units_title

data class SettingsOption(
    val type: SettingType,
    val icon: ImageVector,
    val title: StringResource,
    val subtitle: StringResource,
)

fun getSettingsOptions(): List<SettingsOption> {
    return listOf(
        SettingsOption(
            type = SettingType.PROFILE,
            icon = Icons.Outlined.Store,
            title = Res.string.tx_profile_title,
            subtitle = Res.string.tx_profile_subtitle,
        ),
        SettingsOption(
            type = SettingType.CATEGORIES,
            icon = Icons.Outlined.Category,
            title = Res.string.tx_categories_title,
            subtitle = Res.string.tx_categories_subtitle,
        ),
        SettingsOption(
            type = SettingType.BRANDS,
            icon = Icons.Outlined.NewLabel,
            title = Res.string.tx_brands_title,
            subtitle = Res.string.tx_brands_subtitle,
        ),
        SettingsOption(
            type = SettingType.UNITS,
            icon = Icons.Outlined.SquareFoot,
            title = Res.string.tx_units_title,
            subtitle = Res.string.tx_units_subtitle,
        ),
        SettingsOption(
            type = SettingType.SYNC_DATA,
            icon = Icons.Outlined.Sync,
            title = Res.string.tx_sync_title,
            subtitle = Res.string.tx_sync_subtitle,
        ),
        SettingsOption(
            type = SettingType.EXPORT_DATA,
            icon = Icons.Outlined.Download,
            title = Res.string.tx_export_title,
            subtitle = Res.string.tx_export_subtitle,
        ),
    )
}

fun getLogoutOption(): SettingsOption {
    return SettingsOption(
        type = SettingType.LOGOUT,
        icon = Icons.AutoMirrored.Outlined.Logout,
        title = Res.string.tx_logout_title,
        subtitle = Res.string.tx_logout_subtitle,
    )
}