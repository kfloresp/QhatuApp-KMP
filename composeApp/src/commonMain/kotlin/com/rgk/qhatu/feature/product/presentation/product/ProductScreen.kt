package com.rgk.qhatu.feature.product.presentation.product

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
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.presentation.product.component.ItemProductAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    uiState: ProductUiState,
    onQueryChange: (String) -> Unit,
    onItemClick: (Product) -> Unit,
    isRefreshing: Boolean,
    onPullRefresh: () -> Unit,
) {
    val query = if (uiState is ProductUiState.Success) uiState.query else ""

    RefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { onPullRefresh() }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            when (uiState) {
                is ProductUiState.Loading -> {
                    ShimmerListVertical()
                }

                is ProductUiState.Error -> {
                    ErrorSection(uiState.message)
                }

                is ProductUiState.Success -> {
                    SearchBar(
                        query = query,
                        onQueryChange = onQueryChange
                    )
                    ActionableListContent(
                        modifier = Modifier,
                        items = uiState.result,
                        itemKey = { it.id },
                        onItemClick = onItemClick,
                        onActionClick = {},
                        itemContent = { item, onClick, onAction ->
                            ItemProductAction(
                                product = item,
                                onActionClick = onAction,
                            )
                        }
                    )
                }

                ProductUiState.Empty -> {
                    EmptySection()
                }

            }
        }
    }
}