package com.rgk.qhatu.common.components.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> ActionableListContent(
    modifier: Modifier = Modifier,
    items: List<T>,
    itemToLabel: (T) -> String,
    itemToKey: (T) -> Any,
    onItemClick: (T) -> Unit,
    onActionClick: (T) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            bottom = 80.dp
        ),
    ) {
        itemsIndexed(
            items = items,
            key = { _, item -> itemToKey(item) }
        ) { _, item ->
            ItemAction(
                label = itemToLabel(item),
                onItemClick = { onItemClick(item) },
                onActionClick = { onActionClick(item) },
            )
        }
    }
}
