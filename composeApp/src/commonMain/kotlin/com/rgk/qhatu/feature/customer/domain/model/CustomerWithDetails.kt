package com.rgk.qhatu.feature.customer.domain.model

sealed class CustomerWithDetails {
    data class PersonWithCustomer(
        val person: Person,
        val customer: Customer
    ) : CustomerWithDetails()

    data class CompanyWithCustomer(
        val company: Company,
        val customer: Customer
    ) : CustomerWithDetails()
}

fun CustomerWithDetails.updatePerson(
    block: (Person) -> Person
): CustomerWithDetails {
    return when (this) {
        is CustomerWithDetails.PersonWithCustomer -> copy(person = block(person))
        is CustomerWithDetails.CompanyWithCustomer -> this
    }
}

fun CustomerWithDetails.updateCompany(
    block: (Company) -> Company
): CustomerWithDetails {
    return when (this) {
        is CustomerWithDetails.CompanyWithCustomer -> copy(company = block(company))
        is CustomerWithDetails.PersonWithCustomer -> this
    }
}

fun CustomerWithDetails.updateCustomer(
    block: (Customer) -> Customer
): CustomerWithDetails {
    return when (this) {
        is CustomerWithDetails.PersonWithCustomer ->
            copy(customer = block(customer))
        is CustomerWithDetails.CompanyWithCustomer ->
            copy(customer = block(customer))
    }
}
