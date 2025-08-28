package com.rgk.qhatu.feature.sale.domain.model

import com.rgk.qhatu.common.util.formatAmount

data class Sale(
    val operationId: String = "",
    val customerId: String = "",
    val voucherType: SaleVoucherType = SaleVoucherType.SALES_NOTE,
    val voucherOperationNo: String = "",
    val subtotalWithIGV: Double = 0.0,
    val totalDiscounts: Double = 0.0,
    val grandTotal: Double = subtotalWithIGV - totalDiscounts,
    val paymentMethod: SalePaymentMethod = SalePaymentMethod.CASH,
    val paymentOperationNo: String? = null,
    val amountPaid: String? = null,
    val changeReturned: Double? = null,
) {
    val amountPaidFormatted: String
        get() {
            val paid = amountPaid?.toDoubleOrNull() ?: 0.0
            return paid.formatAmount()
        }

      val changeReturnedFormatted: String
        get() {
            val paid = amountPaid?.toDoubleOrNull() ?: 0.0
            val change = changeReturned ?: (paid - grandTotal).takeIf { it >= 0 } ?: 0.0
            return change.formatAmount()
        }

}

enum class SaleVoucherType(val value: String) {
    SALES_NOTE("Nota de venta"),
    //RECEIPT("Boleta"),
    //INVOICE("Factura"),
}

enum class SalePaymentMethod(val value: String) {
    CASH("Efectivo"),
    YAPE("Yape"),
    PLIN("Plin"),
    CREDIT("Crédito"),
    //DEPOSIT("Depósito Bancario"),
}

enum class SaleAmountCash(val value: String) {
    HUNDRED("100"),
    FIFTY("50"),
    TWENTY("20"),
    TEN("10"),
    OTHER("Otro"),
}