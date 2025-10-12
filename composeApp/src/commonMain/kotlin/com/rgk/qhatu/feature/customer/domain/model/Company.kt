package com.rgk.qhatu.feature.customer.domain.model

data class Company(
    val customerId: String = "",
    val companyName: String = "",
) {
    val fullName: String
        get() = companyName

    val firstLetter: String
        get() {
            return companyName.split(" ")
                .filter { it.isNotBlank() }
                .take(2)
                .mapNotNull { it.firstOrNull()?.uppercaseChar() }
                .joinToString("")
        }
}