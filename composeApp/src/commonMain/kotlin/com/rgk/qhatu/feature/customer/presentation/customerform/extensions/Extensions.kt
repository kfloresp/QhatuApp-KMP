package com.rgk.qhatu.feature.customer.presentation.customerform.extensions

import com.rgk.qhatu.feature.customer.domain.model.CustomerWithDetails

fun CustomerWithDetails.markAsDeleted(): CustomerWithDetails = when (this) {
    is CustomerWithDetails.PersonWithCustomer ->
        copy(customer = customer.copy(isDeleted = true))
    is CustomerWithDetails.CompanyWithCustomer ->
        copy(customer = customer.copy(isDeleted = true))
}
