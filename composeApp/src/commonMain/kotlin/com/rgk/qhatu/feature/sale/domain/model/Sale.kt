package com.rgk.qhatu.feature.sale.domain.model

data class Sale(
    val operationId: String = "",
    val customerId: String = "",
    val voucherType: SaleVoucherType = SaleVoucherType.SALES_NOTE,
    val voucherOperationNo: String = "",
    val subtotalWithIGV: Double = 0.0,
    val totalDiscounts: Double = 0.0,
    val grandTotal: Double = subtotalWithIGV - totalDiscounts,
    val paymentMethod: SalePaymentMethod = SalePaymentMethod.EFECTIVO,
    val paymentOperationNo: String? = null,
    val amountPaid: String? = null,
    val changeReturned: Double? = null,
)

enum class SaleVoucherType(val value: String) {
    SALES_NOTE("Nota de venta"),
    RECEIPT("Boleta"),
    INVOICE("Factura"),
}

enum class SalePaymentMethod(val value: String) {
    EFECTIVO("Efectivo"),
    YAPE("Yape"),
    PLIN("Plin"),
    DEPOSITO("Depósito Bancario"),
}