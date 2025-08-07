package com.rgk.qhatu.feature.payment.presentation.payment

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
import com.rgk.qhatu.feature.payment.domain.model.Payment
import com.rgk.qhatu.feature.payment.presentation.payment.component.ItemPaymentAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    uiState: PaymentUiState,
    onQueryChange: (String) -> Unit,
    onItemClick: (Payment) -> Unit,
    isRefreshing: Boolean,
    onPullRefresh: () -> Unit,
) {
    val query = if (uiState is PaymentUiState.Success) uiState.query else ""

    RefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { onPullRefresh() }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            when (uiState) {
                is PaymentUiState.Loading -> {
                    ShimmerListVertical()
                }

                is PaymentUiState.Error -> {
                    ErrorSection(uiState.message)
                }

                is PaymentUiState.Success -> {
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
                            ItemPaymentAction(
                                customerPayment = item.clientId,
                                datePayment = item.paymentDate.toString(),
                                methodPayment = item.paymentMethodId,
                                onItemClick = onClick,
                                onActionClick = onAction
                            )
                        }
                    )
                }

                PaymentUiState.Empty -> {
                    EmptySection()
                }
            }
        }
    }
}