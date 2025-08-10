package com.rgk.qhatu.feature.customer.domain.model

import com.rgk.qhatu.common.util.formatAmount
import com.rgk.qhatu.common.util.orZero

data class Customer(
    val id: String = "",

    val businessName: String? = null,

    val firstName: String? = null,

    val lastName: String? = null,

    val motherLastName: String? = null,

    val documentType: String = "",

    val documentNumber: String = "",

    val phoneNumber: String? = null,

    val address: String? = null,

    val email: String? = null,

    val pendingAmount: Double? = 0.0,

    val isSupplier: Boolean = false,

    val isActive: Boolean = false,

    val isSynced: Boolean = false,

    val isDeleted: Boolean = false,

    val lastUpdated: Long = 0L,
) {
    private val fullName: String
        get() = listOfNotNull(firstName, lastName, motherLastName)
            .joinToString(" ")

    val nameCustomer: String
        get() = if (isSupplier) businessName.orEmpty() else fullName

    val pendingCustomer: String
        get() = if (pendingAmount.orZero() > 0.0) pendingAmount.orZero()
            .formatAmount(CURRENCY_SYMBOL) else NO_DEBT

    val havePendingAmount: Boolean
        get() = pendingAmount.orZero() > 0.0
    val havePendingCustomer: Boolean
        get() = if (pendingAmount.orZero() > 0.0) true else false

    val firstLetterCustomer: String
        get() = if (isSupplier) {
            businessName
                ?.split(" ")
                ?.filter { it.isNotBlank() }
                ?.take(2)
                ?.mapNotNull { it.firstOrNull()?.uppercaseChar() }
                ?.joinToString("") ?: ""
        } else {
            val firstInitial = firstName?.firstOrNull()?.uppercaseChar() ?: ""
            val lastInitial = lastName?.firstOrNull()?.uppercaseChar() ?: ""
            "$firstInitial$lastInitial"
        }
}

private val NO_DEBT = "Sin deuda"
private val CURRENCY_SYMBOL = "S/."