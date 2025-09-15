package com.rgk.qhatu.feature.customer.domain.model

data class Person(
    val customerId: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val motherLastName: String = "",
){
    val fullName: String
        get() = listOfNotNull(firstName, lastName, motherLastName)
            .joinToString(" ")

    val firstLetter: String
        get()  {
            val firstInitial = firstName.firstOrNull()?.uppercaseChar() ?: ""
            val lastInitial = lastName.firstOrNull()?.uppercaseChar() ?: ""
            return "$firstInitial$lastInitial"
        }
}