package com.rgk.qhatu.feature.setting.presentation.setting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.feature.setting.presentation.setting.component.SettingType
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.ic_question
import qhatuapp.composeapp.generated.resources.tx_global_cancel
import qhatuapp.composeapp.generated.resources.tx_logout_confirm
import qhatuapp.composeapp.generated.resources.tx_logout_prompt

@Serializable
data object SettingDestination

internal fun NavGraphBuilder.settingDestination(
    onStoreClick: () -> Unit,
    onCategoriesClick: () -> Unit,
    onBrandsClick: () -> Unit,
    onUnitsClick: () -> Unit,
    onSyncDataClick: () -> Unit,
    onExportDataClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    composable<SettingDestination> {
        var showCloseSession by remember { mutableStateOf(false) }
        SettingScreen(
            onOptionClick = { settingType ->
                when (settingType) {
                    SettingType.STORE -> {
                        onStoreClick()
                    }

                    SettingType.CATEGORY -> {
                        onCategoriesClick()
                    }

                    SettingType.BRAND -> {
                        onBrandsClick()
                    }

                    SettingType.UNIT_MEASURE -> {
                        onUnitsClick()
                    }

                    SettingType.SYNC_DATA -> {
                        onSyncDataClick()
                    }

                    SettingType.EXPORT_DATA -> {
                        onExportDataClick()
                    }

                    SettingType.LOGOUT -> {
                        showCloseSession = true
                    }
                }
            }
        )
        if (showCloseSession) {
            ConfirmDialog(
                title = stringResource(Res.string.tx_logout_prompt),
                primaryButtonText = stringResource(Res.string.tx_logout_confirm),
                onPrimaryClick = {
                    onLogoutClick()
                },
                secondaryButtonText = stringResource(Res.string.tx_global_cancel),
                onSecondaryClick = {
                    showCloseSession = false
                },
                onDismiss = {
                    showCloseSession = false
                }
            )
        }
    }
}