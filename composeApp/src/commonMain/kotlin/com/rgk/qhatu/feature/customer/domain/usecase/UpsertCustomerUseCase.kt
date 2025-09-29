package com.rgk.qhatu.feature.customer.domain.usecase

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.customer.domain.model.CustomerWithDetails
import com.rgk.qhatu.feature.customer.domain.repository.CompanyRepository
import com.rgk.qhatu.feature.customer.domain.repository.CustomerRepository
import com.rgk.qhatu.feature.customer.domain.repository.PersonRepository

class UpsertCustomerUseCase(
    private val personRepository: PersonRepository,
    private val companyRepository: CompanyRepository,
    private val customerRepository: CustomerRepository,
) {
    suspend operator fun invoke(
        customerWithDetails: CustomerWithDetails,
    ): SyncResult<Unit> = safeCall {
        when (customerWithDetails) {
            is CustomerWithDetails.CompanyWithCustomer -> {
                val company = customerWithDetails.company
                val customer = customerWithDetails.customer

                val customerId = customer.customerId.ifEmpty { generateUUID() }
                val type = if (customer.customerId.isEmpty()) {
                    TypeUpsert.NEW
                } else {
                    TypeUpsert.UPDATE
                }

                val newCustomer = customer.copy(customerId = customerId)
                val newCompany = company.copy(customerId = customerId)
                customerRepository.upsertCustomer(newCustomer, type)
                companyRepository.upsertCompany(newCompany, type)
            }

            is CustomerWithDetails.PersonWithCustomer -> {
                val person = customerWithDetails.person
                val customer = customerWithDetails.customer

                val customerId = customer.customerId.ifEmpty { generateUUID() }
                val type = if (customer.customerId.isEmpty()) {
                    TypeUpsert.NEW
                } else {
                    TypeUpsert.UPDATE
                }

                val newCustomer = customer.copy(customerId = customerId)
                val newPerson = person.copy(customerId = customerId)
                customerRepository.upsertCustomer(newCustomer, type)
                personRepository.upsertPerson(newPerson, type)
            }
        }
    }
}