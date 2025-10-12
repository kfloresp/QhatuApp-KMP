package com.rgk.qhatu.feature.customer.domain.usecase

import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.CustomerWithDetails
import com.rgk.qhatu.feature.customer.domain.model.DocumentType
import com.rgk.qhatu.feature.customer.domain.repository.CompanyRepository
import com.rgk.qhatu.feature.customer.domain.repository.CustomerRepository
import com.rgk.qhatu.feature.customer.domain.repository.PersonRepository

class GetCustomersUseCase(
    private val repository: CustomerRepository,
    private val personRepository: PersonRepository,
    private val companyRepository: CompanyRepository,
) {
    suspend operator fun invoke(): SyncResult<List<CustomerWithDetails>> = safeCall {
        val customers = repository.getAllCustomers()

        customers.mapNotNull { customer ->
            if (customer.documentType == DocumentType.RUC) {
                companyRepository.getCompanyWithCustomer(customer.customerId)?.let {
                    CustomerWithDetails.CompanyWithCustomer(it.company, it.customer)
                }
            } else {
                personRepository.getPersonWithCustomer(customer.customerId)?.let {
                    CustomerWithDetails.PersonWithCustomer(it.person, it.customer)
                }
            }
        }
    }
}