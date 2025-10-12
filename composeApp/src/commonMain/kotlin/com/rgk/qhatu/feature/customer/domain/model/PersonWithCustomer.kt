package com.rgk.qhatu.feature.customer.domain.model

data class PersonWithCustomer(
    val person: Person,
    val customer: Customer,
)
//val GENERIC_CUSTOMER = Customer(
//    customerId = "00000000",
//    firstName = "Consumidor",
//    lastName = "General",
//    documentType = DocumentType.DNI,
//    documentNumber = "00000000",
//    isActive = true,
//    isSynced = true,
//)