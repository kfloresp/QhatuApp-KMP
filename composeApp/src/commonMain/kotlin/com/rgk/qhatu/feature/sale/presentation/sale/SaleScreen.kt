package com.rgk.qhatu.feature.sale.presentation.sale

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.datepicker.toFormattedDate
import com.rgk.qhatu.common.components.empty.EmptySection
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.list.ActionableListContent
import com.rgk.qhatu.common.components.search.SearchBar
import com.rgk.qhatu.common.components.shimmer.ShimmerListVertical
import com.rgk.qhatu.feature.sale.presentation.sale.component.ItemSaleComponent

@Composable
fun SaleScreen(
    uiState: SaleUiState, onQueryChange: (String) -> Unit,
    onItemClick: (String) -> Unit,
    onActionClick: (String) -> Unit,
) {
    val query = if (uiState is SaleUiState.Success) uiState.query else ""

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        when (uiState) {
            is SaleUiState.Loading -> {
                ShimmerListVertical()
            }

            is SaleUiState.Error -> {
                ErrorSection(uiState.message)
            }

            is SaleUiState.Empty -> {
                EmptySection()
            }

            is SaleUiState.Success -> {
                SearchBar(
                    query = query,
                    onQueryChange = onQueryChange,
                )
                Spacer(Modifier.height(8.dp))
                ActionableListContent(
                    modifier = Modifier,
                    items = uiState.result,
                    itemKey = { itemKey ->
                        itemKey.sale.operationId
                    },
                    onItemClick = { itemClick ->
                        onItemClick(itemClick.sale.operationId)
                    },
                    onActionClick = { actionClick ->
                        onItemClick
                    },
                    itemContent = { data, onClick, onAction ->
                        ItemSaleComponent(
                            top = data.sale.operationIdFormatted + " - " + data.operation.status.value,
                            title = data.operation.operationDate.toFormattedDate(),
                            subTitle = data.sale.amountPaidFormatted + " - " + data.sale.paymentMethod.value,
                            firstLetter = data.operation.type.value,
                            onItemClick = onClick,
                            onActionClick = { onActionClick(data.sale.operationId) },
                        )
                    }
                )
            }
        }
    }
}