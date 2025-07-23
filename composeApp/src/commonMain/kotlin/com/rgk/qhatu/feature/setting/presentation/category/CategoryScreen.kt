package com.rgk.qhatu.feature.setting.presentation.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.rgk.qhatu.common.components.list.ActionableListContent
import com.rgk.qhatu.common.components.refresh.RefreshBox
import com.rgk.qhatu.common.components.search.SearchBar
import com.rgk.qhatu.common.components.shimmer.ShimmerListVertical
import com.rgk.qhatu.feature.setting.domain.model.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    uiState: CategoryUiState,
    onQueryChange: (String) -> Unit,
    onItemClick: (Category) -> Unit,
    onActionClick: (Category) -> Unit,
    isRefreshing: Boolean,
    onPullRefresh: () -> Unit,
) {
    val query = if (uiState is CategoryUiState.Success) uiState.query else ""

    RefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { onPullRefresh() }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (!isRefreshing) {
                SearchBar(
                    query = query,
                    onQueryChange = onQueryChange
                )
            }

            when (uiState) {
                is CategoryUiState.Loading -> {
                    ShimmerListVertical()
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
                        itemToLabel = { it.name },
                        itemToKey = { it.id },
                        isSyncing = false,
                        onItemClick = onItemClick,
                        onActionClick = onActionClick,
                    )
                }
            }
        }
    }
}