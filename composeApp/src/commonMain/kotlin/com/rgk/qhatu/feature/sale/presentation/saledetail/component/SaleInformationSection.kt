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
fun SaleInformationSection(voucherOperationNo: String, dateReceipt: String) {
    Column(Modifier.fillMaxWidth()) {
        SectionHeader(text = "Detalles del recibo")
        DetailRow(
            label = "Nro Recibo",
            value = voucherOperationNo
        )
        DetailRow(
            label = "Fecha",
            value = dateReceipt
        )
    }
}

@Preview
@Composable
fun SaleInformationSectionPreview() {
    QhatuTheme {
        SaleInformationSection(voucherOperationNo = "123456", dateReceipt = "01/01/2024 12:00 PM")
    }
}