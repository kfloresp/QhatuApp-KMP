package com.rgk.qhatu.feature.payment.presentation.paymentform

import com.rgk.qhatu.feature.payment.domain.model.Payment

data class PaymentFormValidationState(
    val fields: Payment = Payment(),
    val isValid: Boolean = false,
)