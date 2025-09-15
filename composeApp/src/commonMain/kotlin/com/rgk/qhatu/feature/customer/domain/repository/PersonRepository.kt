package com.rgk.qhatu.feature.customer.domain.repository

import com.rgk.qhatu.feature.customer.domain.model.Person
import com.rgk.qhatu.feature.customer.domain.model.PersonWithCustomer

interface PersonRepository {
    suspend fun insertPerson(person: Person)
    suspend fun getPersonWithCustomer(customerId: String): PersonWithCustomer?
    suspend fun getAllPersonsWithCustomer(): List<PersonWithCustomer>
}