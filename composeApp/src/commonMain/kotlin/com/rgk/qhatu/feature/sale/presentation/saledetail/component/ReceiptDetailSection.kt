package com.rgk.qhatu.feature.sale.presentation.saledetail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rgk.qhatu.common.theme.QhatuTheme
import com.rgk.qhatu.feature.product.presentation.productform.DetailRow
import com.rgk.qhatu.feature.product.presentation.productform.SectionHeader
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ReceiptDetailSection(operationId: String, totalPaid: String, paymentMethod: String) {
    Column(Modifier.fillMaxWidth()) {
        SectionHeader(text = "Información de venta")
        DetailRow(
            label = "ID",
            value = operationId
        )
        DetailRow(
            label = "Total pagado",
            value = totalPaid
        )
        DetailRow(
            label = "Método de pago",
            value = paymentMethod
        )
    }
}

@Preview
@Composable
fun ReceiptDetailSectionPreview() {
    QhatuTheme {
        ReceiptDetailSection(operationId = "123456", totalPaid = "S/ 150.00", paymentMethod = "Tarjeta de crédito")
    }
}