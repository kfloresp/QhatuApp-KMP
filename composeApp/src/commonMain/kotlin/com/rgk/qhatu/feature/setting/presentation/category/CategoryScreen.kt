package com.rgk.qhatu.feature.setting.presentation.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.rgk.qhatu.common.components.list.ActionableListContent
import com.rgk.qhatu.common.components.search.SearchBar
import com.rgk.qhatu.feature.setting.domain.model.Category

@Composable
fun CategoryScreen(
    uiState: CategoryUiState,
    onQueryChange: (String) -> Unit,
    onItemClick: (Category) -> Unit,
    onActionClick: (Category) -> Unit,
) {
    val query = if (uiState is CategoryUiState.Success) uiState.query else ""

    Column(Modifier.fillMaxSize()) {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange
        )

        when (uiState) {
            is CategoryUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is CategoryUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = uiState.message, color = Color.Red)
                }
            }

            is CategoryUiState.Success -> {
                ActionableListContent(
                    modifier = Modifier,
                    items = uiState.result,
                    itemToLabel = { it.nombre },
                    itemToKey = { it.id },
                    isSyncing = false,
                    onItemClick = onItemClick,
                    onActionClick = onActionClick,
                )
            }
        }
    }
}