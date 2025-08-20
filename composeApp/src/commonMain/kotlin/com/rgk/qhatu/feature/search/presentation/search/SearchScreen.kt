package com.rgk.qhatu.feature.search.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.empty.EmptySection
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.refresh.RefreshBox
import com.rgk.qhatu.common.components.search.SearchBar
import com.rgk.qhatu.common.components.shimmer.ShimmerListVertical
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.presentation.product.ProductUiState
import com.rgk.qhatu.feature.product.presentation.product.component.ItemProductCard
import com.rgk.qhatu.feature.product.presentation.product.component.ProductActions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
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

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(
                            bottom = 80.dp,
                            start = 8.dp,
                            end = 8.dp,
                        )
                    ) {
                        items(uiState.result) { product ->
                            ItemProductCard(product = product, productActions = object:
                                ProductActions {
                                override fun onAddProduct(
                                    product: Product,
                                    count: Int,
                                ) {

                                }

                                override fun onUpdateQuantityProduct(
                                    product: Product,
                                    count: Int,
                                ) {

                                }

                                override fun onRemoveProduct(product: Product) {

                                }

                            }) {
                                onItemClick(it)
                            }
                        }
                    }
                }

                ProductUiState.Empty -> {
                    EmptySection()
                }

            }
        }
    }
}