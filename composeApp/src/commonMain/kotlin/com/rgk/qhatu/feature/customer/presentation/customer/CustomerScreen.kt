package com.rgk.qhatu.feature.customer.presentation.customer

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
import com.rgk.qhatu.feature.customer.domain.model.Customer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerScreen(
    uiState: CustomerUiState,
    onQueryChange: (String) -> Unit,
    onItemClick: (Customer) -> Unit,
    onActionClick: (Customer) -> Unit,
    isRefreshing: Boolean,
    onPullRefresh: () -> Unit,
) {
    val query = if (uiState is CustomerUiState.Success) uiState.query else ""

    RefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { onPullRefresh() }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            when (uiState) {
                is CustomerUiState.Loading -> {
                    ShimmerListVertical()
                }

                is CustomerUiState.Error -> {
                    ErrorSection(uiState.message)
                }

                is CustomerUiState.Success -> {
                    SearchBar(
                        query = query,
                        onQueryChange = onQueryChange
                    )
                    ActionableListContent(
                        modifier = Modifier,
                        items = uiState.result,
                        itemToLabel = { it.firstName.toString() },
                        itemToKey = { it.id },
                        onItemClick = onItemClick,
                        onActionClick = onActionClick,
                    )
                }

                CustomerUiState.Empty -> {
                    EmptySection()
                }
            }
        }
    }
}