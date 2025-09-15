package com.rgk.qhatu.feature.customer.domain.usecase

import com.rgk.qhatu.common.exception.QhatuException
import com.rgk.qhatu.common.extension.safeCall
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.CustomerWithDetails
import com.rgk.qhatu.feature.customer.domain.model.DocumentType
import com.rgk.qhatu.feature.customer.domain.repository.CompanyRepository
import com.rgk.qhatu.feature.customer.domain.repository.PersonRepository

class GetCustomerWithDetailsByIdUseCase(
    private val personRepository: PersonRepository,
    private val companyRepository: CompanyRepository,
) {
    suspend operator fun invoke(
        customerId: String,
        documentType: String,
    ): SyncResult<CustomerWithDetails> = safeCall {
        when (documentType) {
            DocumentType.RUC.value -> {
                val companyWithCustomer = companyRepository.getCompanyWithCustomer(customerId)
                    ?: throw QhatuException.Exception("No se encontró la empresa con id $customerId")
                CustomerWithDetails.CompanyWithCustomer(
                    companyWithCustomer.company,
                    companyWithCustomer.customer
                )
            }
            else -> {
                val personWithCustomer = personRepository.getPersonWithCustomer(customerId)
                    ?: throw QhatuException.Exception("No se encontró la persona con id $customerId")
                CustomerWithDetails.PersonWithCustomer(
                    personWithCustomer.person,
                    personWithCustomer.customer
                )
            }
        }
    }
}
