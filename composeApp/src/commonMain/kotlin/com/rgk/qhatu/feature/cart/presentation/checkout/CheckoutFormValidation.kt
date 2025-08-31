package com.rgk.qhatu.feature.cart.presentation.checkout

import com.rgk.qhatu.feature.sale.domain.model.SaleWithOperation

data class CheckoutFormValidationState(
    val fields: SaleWithOperation = SaleWithOperation(),
    val isValid: Boolean = false,
)