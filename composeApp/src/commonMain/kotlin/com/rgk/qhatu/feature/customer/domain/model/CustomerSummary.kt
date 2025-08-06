package com.rgk.qhatu.feature.customer.domain.model

data class CustomerSummary(
    val idCustomerSummary: String,
    val idCustomer: String,
    val type: String,
    val date: String,
    val amount: String,
)