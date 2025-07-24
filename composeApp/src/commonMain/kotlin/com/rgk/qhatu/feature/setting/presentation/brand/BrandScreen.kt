package com.rgk.qhatu.feature.setting.presentation.brand

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rgk.qhatu.common.components.empty.EmptySection
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.list.ActionableListContent
import com.rgk.qhatu.common.components.refresh.RefreshBox
import com.rgk.qhatu.common.components.search.SearchBar
import com.rgk.qhatu.common.components.shimmer.ShimmerListVertical
import com.rgk.qhatu.feature.setting.domain.model.Brand

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandScreen(
    uiState: BrandUiState,
    onQueryChange: (String) -> Unit,
    onItemClick: (Brand) -> Unit,
    onActionClick: (Brand) -> Unit,
    isRefreshing: Boolean,
    onPullRefresh: () -> Unit,
) {
    val query = if (uiState is BrandUiState.Success) uiState.query else ""

    RefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { onPullRefresh() }
    ) {
        Column(Modifier.fillMaxSize()) {
            when (uiState) {
                is BrandUiState.Loading -> {
                    ShimmerListVertical()
                }

                is BrandUiState.Error -> {
                    ErrorSection(uiState.message)
                }

                is BrandUiState.Success -> {
                    SearchBar(
                        query = query,
                        onQueryChange = onQueryChange
                    )
                    ActionableListContent(
                        modifier = Modifier,
                        items = uiState.result,
                        itemToLabel = { it.name },
                        itemToKey = { it.id },
                        onItemClick = onItemClick,
                        onActionClick = onActionClick,
                    )
                }

                BrandUiState.Empty -> {
                    EmptySection()
                }
            }
        }
    }
}