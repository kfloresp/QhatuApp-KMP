package com.rgk.qhatu.feature.payment.domain.model

import com.rgk.qhatu.common.components.datepicker.toEpochMilliseconds
import com.rgk.qhatu.common.components.datepicker.today
import com.rgk.qhatu.common.util.formatAmount
import kotlinx.datetime.Clock

data class Payment(
    val id: String = "",
    val customerId: String = "",
    val customer: String = "",
    val paymentDate: Long = Clock.System.today().toEpochMilliseconds(),
    val amountPaid: String = "",
    val paymentMethodId: String = "",
    val paymentMethod: String = "",
    val comments: String = "",
    val numberOperation: String = "",
    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,
    val lastUpdated: Long = 0L,
) {
    val amountPaidWithCurrency: String
        get() = amountPaid.toDouble().formatAmount(CURRENCY_SYMBOL)
}

private val CURRENCY_SYMBOL = "S/."