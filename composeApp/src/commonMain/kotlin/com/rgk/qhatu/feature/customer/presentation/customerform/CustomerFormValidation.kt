package com.rgk.qhatu.feature.customer.presentation.customerform

import com.rgk.qhatu.feature.customer.domain.model.Customer

data class CustomerFormValidationState(
    val fields: Customer = Customer(),
    val isValid: Boolean = false,
)