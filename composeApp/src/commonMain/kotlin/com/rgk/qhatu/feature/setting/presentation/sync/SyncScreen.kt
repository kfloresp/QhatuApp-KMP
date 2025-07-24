package com.rgk.qhatu.feature.setting.presentation.sync

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.feature.setting.presentation.sync.component.SyncItem
import com.rgk.qhatu.feature.setting.presentation.sync.component.SyncType
import com.rgk.qhatu.feature.setting.presentation.sync.component.SyncsOption
import org.jetbrains.compose.resources.stringResource

@Composable
fun SyncScreen(
    onOptionClick: (SyncType, Int) -> Unit,
    syncOptions: List<SyncsOption>,
) {
    Column(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            itemsIndexed(syncOptions) { index, option ->
                SyncItem(
                    icon = option.icon,
                    title = stringResource(resource = option.title),
                    subtitle = option.subtitle,
                    onClick = { onOptionClick(option.type, index) }
                )
            }
        }
    }
}