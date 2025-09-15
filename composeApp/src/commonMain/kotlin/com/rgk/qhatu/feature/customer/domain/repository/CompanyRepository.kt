package com.rgk.qhatu.feature.customer.domain.repository

import com.rgk.qhatu.feature.customer.domain.model.Company
import com.rgk.qhatu.feature.customer.domain.model.CompanyWithCustomer

interface CompanyRepository {
    suspend fun insertCompany(company: Company)
    suspend fun getCompanyWithCustomer(customerId: String): CompanyWithCustomer?
    suspend fun getAllCompaniesWithCustomer(): List<CompanyWithCustomer>
}