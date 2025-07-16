package com.rgk.qhatu.ui.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.ui.feature.settings.component.SettingType
import com.rgk.qhatu.ui.feature.settings.component.SettingsItem
import com.rgk.qhatu.ui.feature.settings.component.getLogoutOption
import com.rgk.qhatu.ui.feature.settings.component.getSettingsOptions
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingScreen(
    onOptionClick: (SettingType) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val settingsOptions = remember {
            getSettingsOptions()
        }
        val logoutOption = remember {
            getLogoutOption()
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            items(settingsOptions) { option ->
                SettingsItem(
                    icon = option.icon,
                    title = stringResource(resource = option.title),
                    subtitle = stringResource(resource = option.subtitle),
                    onClick = { onOptionClick(option.type) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 1.dp)
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SettingsItem(
                    icon = logoutOption.icon,
                    title = stringResource(resource = logoutOption.title),
                    subtitle = stringResource(resource = logoutOption.subtitle),
                    onClick = { onOptionClick(logoutOption.type) }
                )
            }
        }
    }
}