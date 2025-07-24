package com.rgk.qhatu.feature.setting.presentation.sync

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.feature.setting.presentation.sync.component.SyncItem
import com.rgk.qhatu.feature.setting.presentation.sync.component.SyncType
import com.rgk.qhatu.feature.setting.presentation.sync.component.getSyncsOptions
import org.jetbrains.compose.resources.stringResource

@Composable
fun SyncScreen(
    onOptionClick: (SyncType) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val syncOptions = remember {
            getSyncsOptions()
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            items(syncOptions) { option ->
                SyncItem(
                    icon = option.icon,
                    title = stringResource(resource = option.title),
                    subtitle = stringResource(resource = option.subtitle),
                    onClick = { onOptionClick(option.type) }
                )
            }
        }
    }
}