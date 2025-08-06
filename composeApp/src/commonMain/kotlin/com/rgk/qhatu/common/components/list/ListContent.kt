package com.rgk.qhatu.common.components.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> ListContent(
    modifier: Modifier = Modifier,
    items: List<T>,
    itemKey: (T) -> Any,
    itemContent: @Composable (
        item: T,
    ) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        items(
            items = items,
            key = { item -> itemKey(item) }
        ) { item ->
            itemContent(
                item
            )
        }
    }
}
