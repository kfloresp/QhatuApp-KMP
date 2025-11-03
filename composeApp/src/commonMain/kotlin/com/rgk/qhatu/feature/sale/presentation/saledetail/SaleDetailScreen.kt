package com.rgk.qhatu.feature.sale.presentation.saledetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.datepicker.toFormattedDate
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.feature.sale.presentation.saledetail.component.ItemProductSale
import com.rgk.qhatu.feature.sale.presentation.saledetail.component.ReceiptDetailSection
import com.rgk.qhatu.feature.sale.presentation.saledetail.component.SaleInformationSection

@Composable
fun SaleDetailScreen(uiState: SaleDetailUiState, setLoading: (Boolean) -> Unit) {
    setLoading(uiState is SaleDetailUiState.Loading)

    when (uiState) {
        is SaleDetailUiState.Error -> {
            ErrorSection(uiState.message)
        }

        is SaleDetailUiState.Success -> {
            val data = uiState.result
            Column(Modifier.fillMaxSize().padding(16.dp)) {
                SaleInformationSection(
                    voucherOperationNo = data.sale.voucherOperationNo,
                    dateReceipt = data.operation.operationDate.toFormattedDate()
                )
                ReceiptDetailSection(
                    operationId = data.operation.operationId,
                    totalPaid = data.sale.grandTotalFormatted,
                    paymentMethod = data.sale.paymentMethod.value
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    //TODO: REPAIR
                    items(data.details) { product ->
                        ItemProductSale(
                            detail = product,
                            productName = product.productId,
                            productImageUrl = "",
                            unitMeasure = ""
                        )
                    }
                }
            }
        }

        SaleDetailUiState.Loading -> Unit
    }
}