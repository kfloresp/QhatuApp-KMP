package com.rgk.qhatu.feature.customer.domain.model

import com.rgk.qhatu.common.util.formatAmount
import com.rgk.qhatu.common.util.orZero

data class Customer(
    val customerId: String,
    val documentType: DocumentType = DocumentType.DNI,
    val documentNumber: String,
    val phoneNumber: String? = null,
    val address: String? = null,
    val email: String? = null,
    val pendingAmount: Double? = 0.0,
    val pendingAmountMax: Double? = 0.0,
    val isSupplier: Boolean = false,
    val isActive: Boolean = false,
    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,
    val lastUpdated: Long = 0L,
) {
    val pendingAmountCustomer: String
        get() = if (pendingAmount.orZero() > 0.0) pendingAmount.orZero()
            .formatAmount() else NO_DEBT
    val havePendingAmount: Boolean
        get() = pendingAmount.orZero() > 0.0
}

private val NO_DEBT = "Sin deuda"

enum class DocumentType(value: String) {
    DNI("DNI"),
    RUC("RUC"),
    PASSAPORT("PASAPORTE"),
}
