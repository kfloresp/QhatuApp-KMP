package com.rgk.qhatu.feature.customer.domain.usecase

import com.rgk.qhatu.common.exception.QhatuException
import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.CustomerWithDetails
import com.rgk.qhatu.feature.customer.domain.model.DocumentType
import com.rgk.qhatu.feature.customer.domain.repository.CompanyRepository
import com.rgk.qhatu.feature.customer.domain.repository.CustomerRepository
import com.rgk.qhatu.feature.customer.domain.repository.PersonRepository

class GetCustomerWithDetailsByNameUseCase(
    private val personRepository: PersonRepository,
    private val companyRepository: CompanyRepository,
) {
    suspend operator fun invoke(
        query: String,
    ): SyncResult<List<CustomerWithDetails>> = safeCall {
        val normalizedQuery = query.trim().lowercase()

        val persons = personRepository.searchPersonsWithCustomerByName(normalizedQuery)
            .map { personWithCustomer ->
                CustomerWithDetails.PersonWithCustomer(
                    person = personWithCustomer.person,
                    customer = personWithCustomer.customer,
                )
            }

        val companies = companyRepository.searchCompaniesWithCustomerByName(normalizedQuery)
            .map { companyWithCustomer ->
                CustomerWithDetails.CompanyWithCustomer(
                    company = companyWithCustomer.company,
                    customer = companyWithCustomer.customer,
                )
            }

        val results = (persons + companies)
            .distinctBy { it }

        if (results.isEmpty()) {
            throw QhatuException.Exception("No se encontraron clientes que coincidan con '$query'")
        }

        results
    }
}