package com.rgk.qhatu.feature.sale.domain.model

data class Sale(
    val operationId: String = "",
    val customerId: String = "",
    val vaucherType: SaleVaucherType = SaleVaucherType.SALES_NOTE,
    val vaucherOperationNo: String = "",
    val paymentMethodId: String = "",
    val paymentOperationNo: String? = null,
    val amountPaid: Double? = null,
    val changeReturned: Double? = null,
)

enum class SaleVaucherType(val value: String) {
    SALES_NOTE("Nota de venta"),
    //RECEIPT("Boleta"),
    //INVOICE("Factura"),
}