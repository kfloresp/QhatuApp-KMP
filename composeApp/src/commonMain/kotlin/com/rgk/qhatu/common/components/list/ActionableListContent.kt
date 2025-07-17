package com.rgk.qhatu.common.components.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.rgk.qhatu.common.components.progress.SyncProgressIndicator
import com.rgk.qhatu.common.components.search.SearchBar

@Composable
fun <T> ActionableListContent(
    modifier: Modifier = Modifier,
    items: List<T>,
    itemToLabel: (T) -> String,
    itemToKey: (T) -> Any,
    isSyncing: Boolean,
    onQueryChange: (String) -> Unit,
    onItemClick: (T) -> Unit,
    onEditClick: (T) -> Unit,
    onDeleteClick: (T) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize()) {
        SearchBar(
            query = searchQuery,
            onQueryChange = {
                searchQuery = it
                onQueryChange(it)
            }
        )

        LazyColumn {
            item {
                SyncProgressIndicator(
                    isSyncing = isSyncing
                )
            }
            items(
                items = items,
                key = itemToKey
            ) { item ->
                ItemAction(
                    label = itemToLabel(item),
                    onItemClick = { onItemClick(item) },
                    onEditClick = { onEditClick(item) },
                    onDeleteClick = { onDeleteClick(item) }
                )
            }
        }
    }
}