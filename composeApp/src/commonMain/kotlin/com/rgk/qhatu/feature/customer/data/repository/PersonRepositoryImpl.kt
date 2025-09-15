package com.rgk.qhatu.feature.customer.data.repository

import com.rgk.qhatu.feature.customer.data.database.dao.PersonDao
import com.rgk.qhatu.feature.customer.domain.mapper.toDomain
import com.rgk.qhatu.feature.customer.domain.mapper.toEntity
import com.rgk.qhatu.feature.customer.domain.model.Person
import com.rgk.qhatu.feature.customer.domain.model.PersonWithCustomer
import com.rgk.qhatu.feature.customer.domain.repository.PersonRepository

class PersonRepositoryImpl(
    private val sourceLocal: PersonDao,
) : PersonRepository {
    override suspend fun insertPerson(person: Person) {
        sourceLocal.insertPerson(person.toEntity())
    }

    override suspend fun getPersonWithCustomer(customerId: String): PersonWithCustomer? {
        return sourceLocal.getPersonWithCustomerById(customerId)?.toDomain()
    }

    override suspend fun getAllPersonsWithCustomer(): List<PersonWithCustomer> {
        return sourceLocal.getAllPersonsWithCustomer().map { it.toDomain() }
    }
}