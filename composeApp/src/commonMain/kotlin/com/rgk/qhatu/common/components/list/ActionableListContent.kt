package com.rgk.qhatu.common.components.list
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rgk.qhatu.common.components.progress.SyncProgressIndicator

@Composable
fun <T> ActionableListContent(
    modifier: Modifier = Modifier,
    items: List<T>,
    itemToLabel: (T) -> String,
    itemToKey: (T) -> Any,
    isSyncing: Boolean,
    onItemClick: (T) -> Unit,
    onEditClick: (T) -> Unit,
    onDeleteClick: (T) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            SyncProgressIndicator(isSyncing = isSyncing)
        }

        itemsIndexed(
            items = items,
            key = { _, item -> itemToKey(item) }
        ) { _, item ->
            ItemAction(
                label = itemToLabel(item),
                onItemClick = { onItemClick(item) },
                onEditClick = { onEditClick(item) },
                onDeleteClick = { onDeleteClick(item) }
            )
        }
    }
}
